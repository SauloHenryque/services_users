FROM openjdk:10-jre-slim
VOLUME /tmp
COPY target/sservices_user-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]