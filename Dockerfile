FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app
COPY target/shopping-backend-1.0.0.jar app.jar

FROM gcr.io/distroless/java21-debian11
COPY --from=builder /app/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
