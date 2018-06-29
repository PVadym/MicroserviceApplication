FROM openjdk:8
ADD ./eureka-service/target/eureka-service.jar /app/
ENTRYPOINT ["java", "-jar", "/app/eureka-service.jar"]
EXPOSE 9001
