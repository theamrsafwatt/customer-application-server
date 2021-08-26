FROM openjdk:8-jdk-alpine
MAINTAINER jumia.com
COPY target/customer-application-server-0.0.1.jar docker-customer-application-server-0.0.1.jar
COPY customerapplicationdb.db customerapplicationdb.db
ENTRYPOINT ["java","-jar","docker-customer-application-server-0.0.1.jar"]