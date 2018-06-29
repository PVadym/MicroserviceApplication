FROM openjdk:8
ADD ./config-service/target/config-service.jar /app/
ENTRYPOINT ["java", "-jar", "/app/config-service.jar"]
EXPOSE 9000
