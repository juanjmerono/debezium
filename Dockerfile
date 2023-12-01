FROM maven:3-eclipse-temurin-17-alpine as build

RUN mvn dependency:get -Dmaven.repo.local=/app -Dartifact=com.oracle.database.jdbc:ojdbc8:19.13.0.0.1

FROM quay.io/debezium/server:2.3.4.Final

COPY --from=build /app/com/oracle/database/jdbc/ojdbc8/19.13.0.0.1/ojdbc8-19.13.0.0.1.jar lib/.
