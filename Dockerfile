FROM java:8-jdk-alpine
COPY target/*.jar /usr/quotes.jar
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "quotes.jar"]
