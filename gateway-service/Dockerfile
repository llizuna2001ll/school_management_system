FROM openjdk:17-oracle
LABEL authors="Digital-Zone"
VOLUME /top
ARG JAR_FILE=target/*.jar
COPY ./target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]