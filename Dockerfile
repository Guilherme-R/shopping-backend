FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR
COPY target/shopping-backend-1.0.0.jar app.jar

# Expõe a porta padrão
EXPOSE 8080

# Roda o app com limites de memória
ENTRYPOINT ["java", "-Xmx256m", "-Xms128m", "-jar", "app.jar"]
