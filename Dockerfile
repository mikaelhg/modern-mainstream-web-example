FROM eclipse-temurin:19-jammy AS GRADLE
WORKDIR /build
COPY . /build
RUN --mount=type=cache,id=modern-gradle,target=/root/.gradle \
      ./gradlew clean -s :backend:bootJar

FROM eclipse-temurin:19-jre-jammy
WORKDIR /app
COPY --from=GRADLE /build/backend/build/libs/app.jar /app/backend/app.jar
CMD java -XX:+UseZGC -Xmx64m -Xms64m -jar /app/backend/app.jar
USER 1000:1000
EXPOSE 8082
