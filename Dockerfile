FROM openjdk:18.0.2.1-jdk-oraclelinux8

ADD target/skipper-server.jar skipper-server.jar

ENTRYPOINT ["java", "-jar", "./skipper-server.jar"]

EXPOSE 8090
