FROM openjdk:17
EXPOSE 8086
ADD target/coworkingSpaceBack-0.0.1-SNAPSHOT.jar coworkingSpaceBack-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/coworkingSpaceBack-0.0.1-SNAPSHOT.jar"]