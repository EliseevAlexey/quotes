FROM java:8-jdk-alpine
COPY target/quotes-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "quotes-0.0.1-SNAPSHOT.jar"]
