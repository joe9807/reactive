FROM openjdk:17.0.2-slim
COPY firstservice/target/firstservice-0.0.1-SNAPSHOT.jar /firstservice-0.0.1-SNAPSHOT.jar
CMD java $JAVA_OPTS $SPRING_OPTS -jar /firstservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
