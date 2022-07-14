FROM openjdk:11-jre-slim
COPY ./*.jar app.jar
ENV	PROFILE local
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]