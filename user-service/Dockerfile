FROM openjdk:8
ADD ./user-service/target/user-service.jar /app/
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]
EXPOSE 1111
