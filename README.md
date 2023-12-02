# Debezium UI Test

Debezium Proof of concept

# Build application image

```
mvnw clean spring-boot:build-image
```

# Start Databases (Oracle XE 11)

```
docker-compose up -d
```

Maybe you have to run twice to restart spring app

# Connect to test app

http://localhost:8080/

# Connect to debezium UI

http://localhost:9080/

Check oracle connector

# Connect to kafka ui

http://localhost:9081/

Check topics, consumer and messages


