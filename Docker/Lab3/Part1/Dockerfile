FROM maven:3.10-eclipse-temurin-17-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

RUN chown appuser:appgroup /app

USER appuser

COPY --chown=appuser:appgroup . .

RUN mvn package

EXPOSE 8080

CMD ["java", "-jar", "target/calculator.jar"]
