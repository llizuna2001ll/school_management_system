FROM openjdk:17-oracle
LABEL authors="Digital-Zone"
VOLUME /top
ARG JAR_FILE=target/*.jar
COPY ./target/grade-service-0.0.1-SNAPSHOT.jar grade-service.jar
ENTRYPOINT ["java", "-jar", "/grade-service.jar"]
