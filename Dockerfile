# Dockerfile (Build stage)
FROM maven:3.8.1-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/foruma.war /app/foruma.war
ENTRYPOINT ["java", "-war", "/app/foruma.war"]

FROM tomcat:9.0-jdk11
RUN rm -f /usr/local/tomcat/webapps/root
COPY --from=build /app/target/foruma.war  /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD [ "catalina.sh","run" ]