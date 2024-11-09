FROM gradle:8-jdk17 AS build

LABEL maintainer="Amol Koli <koli.amol54@gmail.com>" \
      version="1.0" \
      description="This is a Spring Boot application for managing players."

# Set up permissions and copy project files
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Build the application
RUN gradle clean build -x test --no-daemon

# Use a distroless image for the final deployable image
FROM gcr.io/distroless/java17-debian11 AS deploy

# Expose the application port
EXPOSE 8083

# Copy the jar file from the build stage
COPY --from=build /home/gradle/src/build/libs/pms-backend-0.0.1-SNAPSHOT.jar /app/deckard-pms-backend.jar

# Start the application
ENTRYPOINT ["java", "-jar", "/app/deckard-pms-backend.jar"]
