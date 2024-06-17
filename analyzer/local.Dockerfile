FROM maven:3.9.6-eclipse-temurin-21-alpine as builder

WORKDIR /opt/app
COPY ./pom.xml /opt/app/pom.xml

COPY ./src /opt/app/src

RUN --mount=type=cache,target=/root/.m2 mvn clean install -DskipTests

FROM eclipse-temurin:21.0.2_13-jre as runner

WORKDIR /app

COPY --from=builder /opt/app/target/analyzer-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "/app/analyzer-0.0.1-SNAPSHOT.jar"]