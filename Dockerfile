# Build stage
FROM ubuntu:latest AS build
LABEL authors="quoct"

# Update package list and install OpenJDK 17 along with necessary tools
RUN apt-get update && apt-get install -y openjdk-17-jdk wget

# Copy source code into the container
COPY . /app

# Change working directory to /app
WORKDIR /app

# List files in /app to verify contents
RUN ls -la /app

# Ensure the mvnw script has execution permissions
RUN chmod +x /app/mvnw

# Build the jar file
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim

# Set author information
LABEL authors="quoct"

# Expose port 9111
EXPOSE 9111

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar /app.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Use CMD to set the active profile if needed (uncomment if using profiles)
# CMD ["--spring.profiles.active=dev"]
