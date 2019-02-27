package com.multidatasoucres;

import static java.util.Collections.singletonMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "firstEntityManagerFactory", transactionManagerRef = "firstTransactionManager", basePackages = "com.multidatasoucres.store.repo")
public class StoreDataSource {

	@Autowired
	private Environment env;
	@Primary
	@Bean(name="storeDatasource")
	public DataSource storeDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver-class-name"));
		dataSource.setUrl(env.getProperty("db1.datasource.url"));
		dataSource.setUsername(env.getProperty("db1.datasource.username"));
		dataSource.setPassword(env.getProperty("db1.datasource.password"));
		return dataSource;
	}


	@Primary
	@Bean(name = "firstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(final EntityManagerFactoryBuilder builder,
			final @Qualifier("storeDatasource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("com.multidatasoucres.store.model")
				.persistenceUnit("firstDb")
				.properties(singletonMap("hibernate.hbm2ddl.auto", "none"))
				.build();
	}
	@Primary
	@Bean(name = "firstTransactionManager")
	public PlatformTransactionManager firstTransactionManager(@Qualifier("firstEntityManagerFactory")
	EntityManagerFactory firstEntityManagerFactory) {
		return new JpaTransactionManager(firstEntityManagerFactory);
	}

}
