# --- 빌드 단계 ---
FROM gradle:8.7.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew bootJar

# --- 실행 단계 ---
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8989
ENTRYPOINT ["java", "-jar", "app.jar"]
