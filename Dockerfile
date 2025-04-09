# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY --from=build /app/target/ecommerce_springboot-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/static /static
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
