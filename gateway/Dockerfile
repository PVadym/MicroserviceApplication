FROM openjdk:8
ADD ./gateway/target/gateway.jar /app/
ENTRYPOINT ["java", "-jar", "/app/gateway.jar"]
EXPOSE 4000
