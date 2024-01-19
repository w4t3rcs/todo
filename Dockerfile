FROM maven:3.6.3-openjdk-17-slim AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:17-jdk-slim-buster

COPY --from=MAVEN_BUILD /docker-multi-stage-build-demo/target/newtodo-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
