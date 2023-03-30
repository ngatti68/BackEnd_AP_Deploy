FROM amazoncorretto:11-alpine-jdk
MAINTAINER NOG
COPY target/backend-rest-jwt-0.0.1-SNAPSHOT.jar backend-rest-jwt-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/backend-rest-jwt-0.0.1-SNAPSHOT.jar"]
