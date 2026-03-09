# ─── Stage 1 : BUILD ────────────────────────────────────────────────────────
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier uniquement le pom.xml du service pour télécharger les dépendances en cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier les sources et compiler
COPY src ./src
RUN mvn clean package -DskipTests

# ─── Stage 2 : RUNTIME ──────────────────────────────────────────────────────
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copier le JAR fat produit par spring-boot:repackage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]