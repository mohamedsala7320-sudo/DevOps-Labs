FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --chown=appuser:appgroup target/calculator.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
