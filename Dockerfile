FROM node:lts-alpine AS NODE
WORKDIR /build
COPY frontend/src/. /build
RUN --mount=type=cache,id=modern-node,target=/build/node_modules \
      npm install && npm run build

FROM adoptopenjdk/openjdk16:alpine AS GRADLE
WORKDIR /build
COPY . /build
RUN --mount=type=cache,id=modern-gradle,target=/root/.gradle \
      ./gradlew clean -s --no-daemon :backend:bootJar

FROM adoptopenjdk/openjdk16:alpine
WORKDIR /app
COPY --from=NODE /build/dist /app/frontend
COPY --from=GRADLE /build/backend/build/libs/app.jar /app/app.jar
ENV SPRING_RESOURCES_STATIC_LOCATIONS "file:///app/frontend"
CMD java -XX:+UseZGC -Xmx32m -Xms32m -jar /app/app.jar
EXPOSE 8082
