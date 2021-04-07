FROM openjdk:latest
MAINTAINER mickaelluiz843@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]

# docker build -t java-application .
# docker run --name ecommerce -e PORT='8080' -e SPRING_PROFILES_ACTIVE='prod' mikkaeru/ecommerce
# docker run -p 8080:8080 --name ecommerce -e PORT='8080' -e SPRING_PROFILES_ACTIVE='prod' mikkaeru/ecommerce