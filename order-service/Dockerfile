# Stage 1: Build
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
# Set the working directory inside the container
WORKDIR /app
# Copy the pom.xml and download dependencies to leverage Docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline
# Copy the source code
COPY src ./src
# Build the Maven project
RUN mvn clean install -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-alpine
# Set the working directory inside the container
WORKDIR /app
# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar
# Expose the port your application listens on (if applicable)
EXPOSE 8080
# Define the command to run the application
ENTRYPOINT ["java","-jar","app.jar"]