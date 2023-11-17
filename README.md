# Debezium Test

Debezium Proof of concept

# Start Databases

docker-compose up -d

# Run application

mvnw clean spring-boot:run

# Connect to test

http://localhost:8080/

# Oracle

Activate ARCHIVELOG

docker exec -it source-database /bin/sh

$sqlplus / as sysdba

SQL> SHUTDOWN IMMEDIATE 
SQL> STARTUP MOUNT
SQL> ALTER DATABASE ARCHIVELOG;
SQL> ALTER DATABASE OPEN;
SQL> ARCHIVE LOG LIST

Connect as user:

ALTER TABLE MYUSER.USERS ADD SUPPLEMENTAL LOG DATA (ALL) COLUMNS;

