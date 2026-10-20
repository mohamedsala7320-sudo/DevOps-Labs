FROM gradle:8.5-jdk17-alpine

WORKDIR /app

COPY . .

RUN gradle test

RUN gradle build

CMD ["java", "-jar", "build/libs/calculator.jar"]
