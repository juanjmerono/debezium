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

# Restart debezium server and check logs

```
docker-compose restart debezium
docker-compose logs -f debezium
```

# Connect to test app

http://localhost:8080/

