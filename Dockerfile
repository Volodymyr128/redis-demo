FROM openjdk:8-jdk-alpine
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar redis-demo-volume/redis-demo-1.0.jar"]