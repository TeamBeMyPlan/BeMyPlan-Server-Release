FROM openjdk:11-jre-slim
COPY ./*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]