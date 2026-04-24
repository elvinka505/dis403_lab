FROM eclipse-temurin:21

WORKDIR /app

RUN mkdir -p /app/logs

COPY target/lab7_docker_http_client-1.0-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
