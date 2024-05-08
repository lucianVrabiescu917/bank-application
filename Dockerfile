FROM maven:3.8.3-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy pom.xml to the container
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application
COPY src src

# Build the application
RUN mvn package -DskipTests

# Use a base image with Java and PostgreSQL
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8091
