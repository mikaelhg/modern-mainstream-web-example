# The Gradle Node plugin doesn't work on Alpine, that's why we build on Ubuntu.

FROM azul/zulu-openjdk:25-latest AS builder
RUN groupadd -r -g 1000 app && useradd -r -u 1000 -g app -s /bin/false -m app
WORKDIR /build
COPY --chown=app:app . /build
RUN chown app:app /build
USER app
RUN mkdir /home/app/.gradle
ENV GRADLE_USER_HOME=/home/app/.gradle
RUN --mount=type=cache,id=modern-gradle,uid=1000,gid=1000,target=/home/app/.gradle \
      ./gradlew --no-daemon --no-configuration-cache clean build agent -x test

FROM azul/zulu-openjdk-alpine:25-jre-latest AS production
RUN addgroup -g 1000 app && adduser -D -u 1000 -G app app
WORKDIR /app
COPY --from=builder /build/backend/build/libs/*.jar /app/
ENV JAVA_TOOL_OPTIONS="-javaagent:/app/opentelemetry-javaagent.jar"
CMD ["java", "--show-version", "-XshowSettings:properties", \
      "-Djdk.serialFilter=!*", \
      "--sun-misc-unsafe-memory-access=allow", \
      "-XX:+UseZGC", "-Xlog:gc+stats", "-Xmx128m", "-Xms128m", "-jar", "/app/app.jar"]
USER app
EXPOSE 8082
