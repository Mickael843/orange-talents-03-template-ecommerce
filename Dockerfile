FROM openjdk:11
MAINTAINER mickaelluiz843@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# docker run --name db -p 3306:3306 -e MYSQL_ROOT_PASSWORD='admin' -e MYSQL_DATABASE='ecommerce_db' mysql
# docker build -t java-application .
# docker run --name ecommerce -e PORT='8080' -e SPRING_PROFILES_ACTIVE='prod' mikkaeru/ecommerce
# docker run -p 8080:8080 --name ecommerce -e PORT='8080' -e SPRING_PROFILES_ACTIVE='prod' mikkaeru/ecommerce