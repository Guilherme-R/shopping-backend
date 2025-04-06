FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/shopping-backend-1.0.0.jar shopping-backend-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shopping-backend-1.0.0.jar"]