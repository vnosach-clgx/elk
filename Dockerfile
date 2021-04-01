FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Vitalii N
WORKDIR /opt/app
ARG JAR_FILE=target/elk-1.0-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
