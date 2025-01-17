FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src /app/src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/prueba-tecnica-0.0.1-SNAPSHOT.jar /app/prueba-tecnica-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "prueba-tecnica-0.0.1-SNAPSHOT.jar"]