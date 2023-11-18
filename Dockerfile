FROM quay.io/debezium/server:2.3.4.Final

COPY ./target/repo/com/oracle/database/jdbc/ojdbc8/19.13.0.0.1/ojdbc8-19.13.0.0.1.jar lib/.
