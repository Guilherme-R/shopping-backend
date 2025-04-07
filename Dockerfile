# Etapa de build
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app
COPY target/shopping-backend-1.0.0.jar app.jar

# Etapa de execução (imagem final enxuta e segura)
FROM gcr.io/distroless/java21-debian11:nonroot
COPY --from=builder /app/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
