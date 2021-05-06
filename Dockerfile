FROM adoptopenjdk/openjdk16:alpine AS BUILD
WORKDIR /build
COPY . /build
RUN --mount=type=cache,target=/root/.gradle \
      ./gradlew build -s --no-daemon bootJar

FROM adoptopenjdk/openjdk16:alpine
WORKDIR /app
COPY --from=BUILD /build/backend/build/libs/app.jar /app/app.jar
CMD java -XX:+UseZGC -Xmx32m -Xms32m -jar /app/app.jar
