FROM bellsoft/liberica-openjdk-alpine:21 as builder
WORKDIR /app
COPY target/shopping-backend-1.0.0.jar app.jar

FROM bellsoft/liberica-openjdk-alpine:21
COPY --from=builder /app/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=aws", "-jar", "app.jar"]
