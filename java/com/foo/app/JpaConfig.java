package com.foo.app;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="com.foo.app.dao")
@EnableTransactionManagement
@ComponentScan(basePackages="com.foo.app.dao") // was commented out
public class JpaConfig {

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource;
		try {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
			dataSource.setUrl("jdbc:derby:app;create=true");
			dataSource.setUsername("");
			dataSource.setPassword("");
			dataSource.setTestOnBorrow(true);
			dataSource.setTestOnReturn(true);
			dataSource.setTestWhileIdle(true);
			dataSource.setTimeBetweenEvictionRunsMillis(1800000);
			dataSource.setMinEvictableIdleTimeMillis(1800000);
			dataSource.setNumTestsPerEvictionRun(3);
		} catch (Exception e) {
			throw new BeanCreationException("Failed to create data source", e);
		}
        return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(new String[] { "com.foo.app.domain" });
		entityManagerFactory.getJpaPropertyMap().put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		entityManagerFactory.getJpaPropertyMap().put("hibernate.show_sql", "true");
		entityManagerFactory.getJpaPropertyMap().put("hibernate.connection.charSet", "UTF-8");
		entityManagerFactory.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistence.class);
		// entityManagerFactory.getJpaPropertyMap().put("hibernate.current_session_context_class", "managed");
		entityManagerFactory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create");
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactory.afterPropertiesSet();
		return entityManagerFactory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
		return transactionManager;
	}
}
