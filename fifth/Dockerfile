FROM openjdk:17.0.2-slim
COPY fifthservice/target/fifthservice-0.0.1-SNAPSHOT.jar /fifthservice-0.0.1-SNAPSHOT.jar
CMD java $JAVA_OPTS $SPRING_OPTS -jar /fifthservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
