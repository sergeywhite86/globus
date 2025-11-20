FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY mail-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]