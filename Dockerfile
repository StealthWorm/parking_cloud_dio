FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install  maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

COPY --from=build /target/cloud-parking-0.0.1-SNAPSHOT.jar app.jar

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Set environment variables for database connection
#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/parking
#ENV SPRING_DATASOURCE_USERNAME=postgres
#ENV SPRING_DATASOURCE_PASSWORD=Kalpon123

