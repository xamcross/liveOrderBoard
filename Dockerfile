FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY build/libs/*SNAPSHOT.jar /app/live-order-board.jar

ENTRYPOINT ["java", "-jar","/app/live-order-board.jar"]