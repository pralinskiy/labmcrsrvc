# Первый этап: сборка приложения
FROM openjdk:17-jdk-alpine AS builder
WORKDIR /app

# Установка Maven
RUN apk add --no-cache maven

# Копирование исходных файлов
COPY pom.xml .
COPY src ./src

# Сборка приложения
RUN mvn clean package -DskipTests

# Второй этап: конечный образ
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]



