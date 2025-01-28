FROM openjdk:17.0.2-slim
COPY sixthservice/target/sixthservice-0.0.1-SNAPSHOT.jar /sixthservice-0.0.1-SNAPSHOT.jar
CMD java $JAVA_OPTS $SPRING_OPTS -jar /sixthservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
