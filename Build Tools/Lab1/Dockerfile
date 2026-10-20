FROM maven:3.10-eclipse-temurin-17-alpine

WORKDIR /app

COPY . .

RUN mvn package

CMD ["java", "-jar", "target/calculator.jar"]

EXPOSE 8080
