FROM ubuntu:20.04

LABEL "project" = "java_c"



COPY pom.xml . 
RUN apt update
RUN apt install maven -y
RUN mvn install
COPY ./src .
#COPY JavaConsumer-1.0-SNAPSHOT.jar
RUN mkdir src
RUN mv main src
RUN mv test src
RUN mvn package

ENV BOOTSTRAP_SERVER localhost:9092
CMD ["java", "-jar", "target/JavaConsumer-1.0-SNAPSHOT.jar"]

#WORKDIR /root/charan

#ADD JavaConsumer-1.0-SNAPSHOT.jar /root/charan
