# Use lightweight Java 17 base image
FROM openjdk:17-jdk-slim

# Set the working directory inside container
WORKDIR /app

# Copy the jar file (make sure it's built)
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]


