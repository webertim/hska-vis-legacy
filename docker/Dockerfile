FROM maven:3.6.3-jdk-11-slim as builder

COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean package

FROM tomcat:8.0
COPY --from=builder /target/EShop-1.0.0.war /usr/local/tomcat/webapps/
COPY ./conf/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

EXPOSE 8080
