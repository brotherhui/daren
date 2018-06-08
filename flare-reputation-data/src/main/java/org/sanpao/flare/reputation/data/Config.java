package org.sanpao.flare.reputation.data;

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
@PropertySource({ "classpath:reputation-data-config.properties" })
@EntityScan({ "org.sanpao.flare.reputation.domain.entity" })
@EnableJpaRepositories(basePackages = { "org.sanpao.flare.reputation.data.dao" }, entityManagerFactoryRef = "reputationEntityManagerFactory", transactionManagerRef = "reputationTransactionManager")
public class Config {

	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.reputation")
	public DataSource reputationDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public EntityManager reputationEntityManager() {
		return reputationEntityManagerFactory().getObject().createEntityManager();
	}

	@Bean
	public JpaVendorAdapter reputationJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean reputationEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(reputationDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(reputationJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("org.sanpao.flare.reputation.domain.entity");
		entityManagerFactoryBean.setPersistenceUnitName("reputationPersistenceUnit");
		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties.getProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager reputationTransactionManager() {
		return new JpaTransactionManager(reputationEntityManagerFactory().getObject());
	}

}
