# Debezium UI Test

Debezium Proof of concept

# Start Databases (MySQL/Oracle)

```
docker-compose --profile database up -d
```

You could use "mysql" and "oracle" profile to start only mysql or oracle databases

# Oracle

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

Connect as myuser:

```
ALTER TABLE MYUSER.USERS ADD SUPPLEMENTAL LOG DATA (ALL) COLUMNS;
```

# Run application

## From oracle to oracle
```
mvnw clean spring-boot:run
```

## From mysql to mysql
```
mvnw clean spring-boot:run -Pmysql
```

## From oracle to mysql
```
mvnw clean spring-boot:run -Pmixom
```

## From mysql to oracle
```
mvnw clean spring-boot:run -Pmixmo
```

# Connect to test app

http://localhost:8080/

# Now run debezium server

Go to https://webhook.site/ to create a webhook
Copy webhook URL in debezium-*.properties

## Download Oracle Driver

```
./mvnw dependency:get -Dmaven.repo.local=target/repo -Dartifact=com.oracle.database.jdbc:ojdbc8:19.13.0.0.1
```

```
docker-compose --profile dbzserver up -d
```

Do some changes in source database and check request in webhook site.

# Now run debezium core

```
docker-compose --profile core up -d
```

