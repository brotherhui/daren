package org.sanpao.flare.payment.data;

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
@PropertySource({ "classpath:payment-data-config.properties" })
@EntityScan({ "org.sanpao.flare.payment.domain.entity" })
@EnableJpaRepositories(basePackages = { "org.sanpao.flare.payment.data.dao" }, entityManagerFactoryRef = "paymentEntityManagerFactory", transactionManagerRef = "paymentTransactionManager")
public class Config {

	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.payment")
	public DataSource paymentDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public EntityManager paymentEntityManager() {
		return paymentEntityManagerFactory().getObject().createEntityManager();
	}

	@Bean
	public JpaVendorAdapter paymentJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean paymentEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(paymentDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(paymentJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("org.sanpao.flare.payment.domain.entity");
		entityManagerFactoryBean.setPersistenceUnitName("paymentPersistenceUnit");
		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties.getProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager paymentTransactionManager() {
		return new JpaTransactionManager(paymentEntityManagerFactory().getObject());
	}

}
