FROM maven:3.8.1-openjdk-16 AS build
COPY src /usr/src/src
COPY pom.xml /usr/src/pom.xml
RUN mvn -f /usr/src/pom.xml clean package

FROM openjdk:16-alpine3.13
COPY --from=build /usr/src/target/fibonacci-1.0-SNAPSHOT.jar /usr/app/fibonacci-1.0-SNAPSHOT.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/usr/app/fibonacci-1.0-SNAPSHOT.jar"]