package com.tistory.seungchul.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.tistory.seungchul",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager"
)
@ComponentScan
public class JPAConfig {

	@Autowired
	private Environment env;
	
	@Value("classpath:user-data.sql")
	private Resource userDataScript;
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.server")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource dataSource() {
		DataSourceProperties properties = dataSourceProperties();
		HikariDataSource source = (HikariDataSource) DataSourceBuilder.create(properties.getClassLoader())
					.driverClassName(properties.getDriverClassName())
					.username(properties.getUsername())
					.password(properties.getPassword())
					.url(properties.getUrl())
					.type(HikariDataSource.class)
					.build();
		source.setMaximumPoolSize(10);
		return source;
		
	}
	
	@Bean
	public JpaVendorAdapter jpaVendor() {
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
	public Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("datasource.server.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("datasource.server.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("datasource.server.hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("datasource.server.hibernate.format_sql"));
        if(StringUtils.isNotEmpty(env.getRequiredProperty("datasource.server.defaultSchema"))){
            properties.put("hibernate.default_schema", env.getRequiredProperty("datasource.server.defaultSchema"));
        }
        return properties;
	}
	

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.tistory.seungchul");
		bean.setJpaVendorAdapter(jpaVendor());
		bean.setJpaProperties(jpaProperties());
		return bean;
		
	}
	
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(emf);
		return tx;
	}
	
	@Bean
	public DatabasePopulator populator() {
		ResourceDatabasePopulator pop = new ResourceDatabasePopulator(userDataScript);
		pop.execute(dataSource());
		return pop;
	}
	
	
	
}
