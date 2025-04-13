# The Gradle Node plugin doesn't work on Alpine, that's why we build on Ubuntu.

FROM azul/zulu-openjdk:21-latest AS builder
RUN groupadd -r -g 1000 app && useradd -r -u 1000 -g app -s /bin/false -m app
WORKDIR /build
COPY --chown=app:app . /build
RUN chown app:app /build
USER app
RUN mkdir /home/app/.gradle
RUN --mount=type=cache,id=modern-gradle,uid=1000,gid=1000,target=/home/app/.gradle \
      ./gradlew clean build agent

FROM azul/zulu-openjdk-alpine:24-latest as production
RUN adduser -D -u 1000 app
WORKDIR /app
COPY --from=builder /build/backend/build/libs/*.jar /app/
ENV JAVA_TOOL_OPTIONS "-javaagent:/app/opentelemetry-javaagent.jar"
CMD java --show-version -XshowSettings:properties -Djdk.serialFilter=!* -XX:+UseZGC -Xlog:gc+stats -Xmx128m -Xms128m -jar /app/app.jar
USER app
EXPOSE 8082
