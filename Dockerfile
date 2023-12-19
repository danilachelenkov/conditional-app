FROM openjdk:22-ea-21-slim-bullseye

EXPOSE 8081

ADD build/libs/conditional-app-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java", "-jar","/myapp.jar"]