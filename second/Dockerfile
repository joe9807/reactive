FROM openjdk:17.0.2-slim
COPY secondservice/target/secondservice-0.0.1-SNAPSHOT.jar /secondservice-0.0.1-SNAPSHOT.jar
CMD java $JAVA_OPTS $SPRING_OPTS -jar /secondservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
