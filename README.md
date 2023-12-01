# Debezium UI Test

Debezium Proof of concept

# Start Databases (Oracle XE 11)

```
docker-compose up -d
```

## Configure Oracle Archive Log

Activate ARCHIVELOG

```
docker exec -it src-database /bin/sh

$sqlplus / as sysdba

SQL> SHUTDOWN IMMEDIATE 
SQL> STARTUP MOUNT
SQL> ALTER DATABASE ARCHIVELOG;
SQL> ALTER DATABASE OPEN;
SQL> ARCHIVE LOG LIST
SQL> ALTER DATABASE ADD SUPPLEMENTAL LOG DATA;
```

After running application (myuser.users table is created) run:

```
ALTER TABLE MYUSER.USERS ADD SUPPLEMENTAL LOG DATA (ALL) COLUMNS;
```

# Run application

```
mvnw clean spring-boot:run
```

# Connect to test app

http://localhost:8080/
