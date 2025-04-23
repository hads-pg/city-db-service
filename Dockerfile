# Use JDK base
FROM amazoncorretto:17-alpine-jdk AS build
WORKDIR /app

# Copy Gradle wrapper and project files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# Run stage
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
