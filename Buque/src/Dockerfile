FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Buque-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Buque-0.0.1-SNAPSHOT.jar"]
# Expose the port the app runs on
EXPOSE 8080