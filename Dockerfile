# Use a base image with OpenJDK
FROM openjdk:17-jdk-alpine

# Create a directory for the app
WORKDIR /app

# Copy the packaged jar file from the target directory
COPY target/coworkingSpaceBack-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy the wait-for-it script to the container
COPY wait-for-it.sh /wait-for-it.sh

# Make the wait-for-it script executable
RUN chmod +x /wait-for-it.sh

# Expose the port on which your application runs
EXPOSE 8080

# Command to run the Spring Boot app using the wait-for-it.sh script
ENTRYPOINT ["/wait-for-it.sh", "mysql-container", "3306", "--", "java", "-jar", "/app/app.jar"]
