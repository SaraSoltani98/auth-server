FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY src/main/java/se/iths/sara/authserver .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]