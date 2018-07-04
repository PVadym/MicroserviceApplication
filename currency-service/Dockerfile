FROM openjdk:8

ADD ./currency-service/target/currency-service.jar /app/
ENTRYPOINT ["java", "-jar", "/app/currency-service.jar"]

EXPOSE 3333
