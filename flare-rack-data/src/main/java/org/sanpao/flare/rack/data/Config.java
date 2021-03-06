package org.sanpao.flare.rack.data;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan
@PropertySource({ "classpath:rack-data-config.properties" })
@EntityScan({ "org.sanpao.flare.rack.domain.entity" })
@EnableJpaRepositories(basePackages = { "org.sanpao.flare.rack.data.dao" }, entityManagerFactoryRef = "rackEntityManagerFactory", transactionManagerRef = "rackTransactionManager")
public class Config {

	@Autowired
	private JpaProperties jpaProperties;

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.rack")
	public DataSource rackDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean
	public EntityManager rackEntityManager() {
		return rackEntityManagerFactory().getObject().createEntityManager();
	}

	@Primary
	@Bean
	public JpaVendorAdapter rackJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
		return adapter;
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean rackEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(rackDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(rackJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("org.sanpao.flare.rack.domain.entity");
		entityManagerFactoryBean.setPersistenceUnitName("rackPersistenceUnit");
		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties.getProperties());
		return entityManagerFactoryBean;
	}

	@Primary
	@Bean
	public PlatformTransactionManager rackTransactionManager() {
		return new JpaTransactionManager(rackEntityManagerFactory().getObject());
	}

}
