FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/timesheet-1.0.4-SNAPSHOT.jar timesheet-1.0.4-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/timesheet-1.0.4-SNAPSHOT.jar"]
