FROM openjdk:17-jdk-slim

COPY target/demo-0.0.1.jar usr/share/app.jar

ENTRYPOINT ["java", "-jar", "usr/share/app.jar"]