# Build
FROM maven:3.6.1-jdk-11-slim AS build
WORKDIR /x-app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY . ./
RUN mvn clean package
 
# Copy Build result & start
FROM openjdk:11-slim
RUN useradd -ms /bin/bash xapp

RUN mkdir -p /usr/lucene
RUN chmod 777 /usr/lucene
RUN chown -R $USER:$USER /usr
RUN mkdir /usr/lucene/indexes
RUN ls -l /usr/lucene
RUN chmod 777 /usr/lucene/indexes
RUN ls -l /usr/lucene

RUN chown root /usr/lucene
RUN chown root /usr/lucene/indexes
USER root
WORKDIR /usr/lucene

RUN mkdir -p /usr/lucene/indexes/com.devcom.models.Question
RUN mkdir -p /usr/lucene/indexes/com.devcom.models.Post

RUN mkdir -p /x-app/usr/lucene/indexes/com.devcom.models.Question
RUN mkdir -p /x-app/usr/lucene/indexes/com.devcom.models.Post

RUN chmod 777 /x-app
RUN chmod 777 /x-app/usr/lucene/indexes/com.devcom.models.Question

WORKDIR /x-app
COPY --from=build /x-app/target/*.jar /x-app/app.jar
 
EXPOSE 8080
 
USER xapp
CMD ["java","-jar", "-Dspring.profiles.active=docker", "/x-app/app.jar"]
