FROM openjdk:17.0.2-slim
COPY fourthservice/target/fourthservice-0.0.1-SNAPSHOT.jar /fourthservice-0.0.1-SNAPSHOT.jar
CMD java $JAVA_OPTS $SPRING_OPTS -jar /fourthservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
