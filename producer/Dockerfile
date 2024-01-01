FROM openjdk:11

LABEL "project" = "java_producer"

RUN apt update
RUN apt install maven -y
RUN mvn install

CMD ["java", "-jar", "JavaConsumer-1.0-SNAPSHOT.jar"]

WORKDIR /root/confluent-for-kubernetes-2.7.0-20230825/helm/confluent-for-kubernetes



