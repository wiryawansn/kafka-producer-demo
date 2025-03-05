FROM gradle:8-jdk17 AS build

WORKDIR /kafka-producer

COPY build.gradle settings.gradle gradlew gradlew.bat /kafka-producer/
COPY gradle /kafka-producer/gradle

COPY src /kafka-producer/src

RUN ./gradlew build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /kafka-producer

COPY --from=build /kafka-producer/build/libs/producer-demo-1.0.0.jar /kafka-producer/producer-demo-1.0.0.jar

EXPOSE 8881

CMD ["java", "-jar", "producer-demo-1.0.0.jar"]