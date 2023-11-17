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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
  basePackages = "es.um.atica.debezium.demo.adapters.jpatarget",
  entityManagerFactoryRef = "userTargetEntityManagerFactory",
  transactionManagerRef = "userTargetTransactionManager")
public class TargetDatabaseConfiguration {
    
    @Bean(name="userTargetProperties")
    @ConfigurationProperties("spring.datasource.target")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name="userTargetDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.target")
    public DataSource datasource(@Qualifier("userTargetProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name="userTargetEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("userTargetDatasource") DataSource dataSource){
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("es.um.atica.debezium.demo.adapters.jpatarget")
                .build();
    }

    @Primary
    @Bean(name = "userTargetTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("userTargetEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

}
