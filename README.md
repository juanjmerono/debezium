# Debezium UI Test

Debezium Proof of concept

# Start Databases (Oracle XE 11)

```
docker-compose up -d
```

# Run application

```
mvnw clean spring-boot:run
```

# Connect to debezium UI

http://localhost:9080/

Configure oracle connector

# Connect to kafka ui

http://localhost:9081/

List topics to show auto creation

# Connect to test app

http://localhost:8080/

