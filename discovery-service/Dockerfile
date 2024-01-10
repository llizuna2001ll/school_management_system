FROM openjdk:17-oracle
LABEL authors="Digital-Zone"
VOLUME /top
ARG JAR_FILE=target/*.jar
COPY ./target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]