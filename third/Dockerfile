FROM openjdk:17.0.2-slim
COPY thirdservice/target/thirdservice-0.0.1-SNAPSHOT.jar /thirdservice-0.0.1-SNAPSHOT.jar
CMD java $JAVA_OPTS $SPRING_OPTS -jar /thirdservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
