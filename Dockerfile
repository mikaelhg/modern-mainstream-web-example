FROM azul/zulu-openjdk:21-latest AS builder
RUN groupadd -r -g 1000 app && useradd -r -u 1000 -g app -s /bin/false -m app
WORKDIR /build
COPY --chown=app:app . /build
RUN chown app:app /build
USER app
RUN mkdir /home/app/.gradle
RUN --mount=type=cache,id=modern-gradle,uid=1000,gid=1000,target=/home/app/.gradle \
      ./gradlew clean build

FROM azul/zulu-openjdk-alpine:23-latest as production
RUN adduser -D -u 1000 app
WORKDIR /app
COPY --from=builder /build/backend/build/libs/app.jar /app/backend/app.jar
CMD java --show-version -XX:+UseZGC -Xmx64m -Xms64m -jar /app/backend/app.jar
USER app
EXPOSE 8082
