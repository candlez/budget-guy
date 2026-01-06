# Build Angular
FROM node:20-alpine AS frontend-build
WORKDIR /frontend

COPY src/main/frontend/package*.json ./
RUN npm ci

COPY src/main/frontend .
RUN npm run build -- --configuration=production

# Build SpringBoot
FROM maven:3.9.9-eclipse-temurin-17 AS backend-build
WORKDIR /backend

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src/main/java src/main/java
COPY src/main/resources src/main/resources

COPY --from=frontend-build /frontend/dist /backend/src/main/resources/static

RUN mvn clean package -DskipTests

# Final
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=backend-build /backend/target/*.war app.war

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]
