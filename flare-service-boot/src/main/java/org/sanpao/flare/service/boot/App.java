package org.sanpao.flare.service.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableConfigurationProperties(JpaProperties.class)
@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
