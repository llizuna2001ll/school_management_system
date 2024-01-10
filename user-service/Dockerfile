FROM openjdk:17-oracle
LABEL authors="Digital-Zone"
VOLUME /top
ARG JAR_FILE=target/*.jar
COPY ./target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]
