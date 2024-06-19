# Use a Java base image
FROM openjdk:11

# Install Docker
RUN apt-get update && apt-get install -y docker.io

# Set the working directory
WORKDIR /app

# Copy the project files and Docker Compose file
COPY . /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the project
RUN mvn clean package

# Start Docker Compose
RUN docker compose up -d

# Run the Karate tests
CMD ["mvn", "test", "-Dtest=KarateTests"]
