FROM openjdk:17
COPY target/advisor-plugin-0.0.1-SNAPSHOT.jar advisor-plugin-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/advisor-plugin-0.0.1-SNAPSHOT.jar"]