FROM openjdk:8-jdk-alpine
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","redis-demo-volume/redis-demo-1.0.jar"]
