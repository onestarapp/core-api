# Use a base image with JDK 17
FROM eclipse-temurin:17-jre-focal

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the host to the container
COPY build/libs/coreapi-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application listens on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
