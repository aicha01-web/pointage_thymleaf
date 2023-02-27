
FROM openjdk:17
ADD target/pointage-0.0.1-SNAPSHOT.jar pointage.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pointage.jar"]