package com.multidatasoucres;

import static java.util.Collections.singletonMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactory",
		transactionManagerRef = "secondTransactionManager", 
		basePackages = "com.multidatasoucres.test.repo")
public class TestDataSourceConfig {
	@Autowired
    private Environment env;

	@Bean(name="testDatasource")
    public DataSource testDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver-class-name"));
        dataSource.setUrl(env.getProperty("db2.datasource.url"));
        dataSource.setUsername(env.getProperty("db2.datasource.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.password"));
        return dataSource;
    }

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                            final @Qualifier("testDatasource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.multidatasoucres.test.model")
                .persistenceUnit("secondDb")
                .properties(singletonMap("hibernate.hbm2ddl.auto", "update"))
                .build();
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager firstTransactionManager(@Qualifier("secondEntityManagerFactory")
                                                              EntityManagerFactory firstEntityManagerFactory) {
        return new JpaTransactionManager(firstEntityManagerFactory);
    }



}
