FROM maven:3.8.3-openjdk-17 as builder

LABEL maintainer="Amol Koli <koli.amol54@gmail.com>" \
      version="1.0" \
      description="This is a Spring Boot application for managing players."

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test -Dorg.gradle.daemon=false

FROM gcr.io/distroless/java17-debian11 AS deployer

COPY --from=builder /app/build/libs/*.jar /app/deckard-pms-backend.jar

EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "/app/deckard-pms-backend.jar"]