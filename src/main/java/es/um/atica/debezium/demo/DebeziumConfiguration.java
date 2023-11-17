package es.um.atica.debezium.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DebeziumConfiguration {

    @Bean
    @Profile("mysql")
    public io.debezium.config.Configuration mysqlConnector() {
        return io.debezium.config.Configuration.create()
        .with("name", "source-mysql-connector")
        .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
        .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
        .with("offset.storage.file.filename", "./target/offsets.dat")
        .with("offset.flush.interval.ms", "60000")
        .with("database.hostname", "localhost")
        .with("database.port", 3305)
        .with("database.user", "root")
        .with("database.password", "root")
        .with("database.dbname", "customerdb")
        .with("database.include.list", "customerdb")
        .with("include.schema.changes", "false")
        .with("database.server.id", "10181")
        .with("database.server.name", "source-mysql-db-server")
        .with("database.history", "io.debezium.relational.history.MemoryDatabaseHistory")
        //.with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
        //.with("database.history.file.filename", "/tmp/dbhistory.dat")
        .with("schema.history.internal", "io.debezium.relational.history.MemorySchemaHistory")
        //.with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
        //.with("schema.history.internal.file.filename", "/tmp/schistory.dat")
        .with("database.allowPublicKeyRetrieval","true")
        .with("topic.prefix","tutorial")
        .build();
    }

    @Bean
    @Profile("oracle")
    public io.debezium.config.Configuration oracleConnector() {
        return io.debezium.config.Configuration.create()
        .with("name", "source-oracle-connector")
        .with("connector.class", "io.debezium.connector.oracle.OracleConnector")
        .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
        .with("offset.storage.file.filename", "./target/offsets.dat")
        .with("offset.flush.interval.ms", "60000")
        .with("database.hostname", "localhost")
        .with("database.port", 1521)
        .with("database.user", "system")
        .with("database.password", "root")
        .with("database.dbname", "XE")
        .with("database.include.list", "XE")
        .with("include.schema.changes", "false")
        .with("database.server.id", "10181")
        .with("database.server.name", "source-oracle-db-server")
        .with("database.history", "io.debezium.relational.history.MemoryDatabaseHistory")
        //.with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
        //.with("database.history.file.filename", "/tmp/dbhistory.dat")
        .with("schema.history.internal", "io.debezium.relational.history.MemorySchemaHistory")
        //.with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
        //.with("schema.history.internal.file.filename", "/tmp/schistory.dat")
        .with("database.allowPublicKeyRetrieval","true")
        .with("topic.prefix","tutorial")
        .build();
    }
}
