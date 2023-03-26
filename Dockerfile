FROM amazoncorretto:8-jdk-alpine
MAINTAINER norbertogatti
COPY target/backend-rest-jwt-0.0.1-SNAPSHOT.jar backend-argentina-programa.jar
ENTRYPOINT ["java","-jar","/backend-argentina-programa.jar"]