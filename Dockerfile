FROM openjdk:8-jdk-alpine
COPY target/customer-application-server-1.0.0.jar docker-customer-application-server-1.0.0.jar
COPY customerapplicationdb.db customerapplicationdb.db
ENTRYPOINT ["java","-jar","docker-customer-application-server-1.0.0.jar"]