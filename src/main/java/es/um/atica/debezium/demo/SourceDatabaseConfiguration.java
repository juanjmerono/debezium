package es.um.atica.debezium.demo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
  basePackages = "es.um.atica.debezium.demo.adapters.jpasource",
  entityManagerFactoryRef = "userSourceEntityManagerFactory",
  transactionManagerRef = "userSourceTransactionManager")
public class SourceDatabaseConfiguration {
    
    @Bean(name="userSourceProperties")
    @ConfigurationProperties("spring.datasource.source")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name="userSourceDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.source")
    public DataSource datasource(@Qualifier("userSourceProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name="userSourceEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("userSourceDatasource") DataSource dataSource){
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("es.um.atica.debezium.demo.adapters.jpasource")
                .build();
    }

    @Bean(name = "userSourceTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("userSourceEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

}
