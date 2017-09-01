FROM maven:3 AS mvn
WORKDIR /build
COPY . /build
ENV MAVEN_OPTS "-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=WARN -Dorg.slf4j.simpleLogger.showDateTime=true -Djava.awt.headless=true"
ENV MAVEN_CLI_OPTS "--batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true"
RUN mvn $MAVEN_CLI_OPTS clean package

FROM java:8-alpine
WORKDIR /app
COPY --from=mvn /build/backend/target/*.jar /app/app.jar
CMD /usr/bin/java -Xmx32m -Xms32m -jar /app/app.jar
