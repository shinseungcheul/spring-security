package com.tistory.seungchul.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.tistory.seungchul",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement(
		mode = AdviceMode.PROXY, proxyTargetClass = true, order = 2
)
public class JPADatabaseConfig {

	@Autowired
	Environment environment;

	@Value("${datasource.server.maxPoolSize:10}")
	private int maxPoolSize;
	
	@Value("classpath:sql/user-data.sql")
	Resource userData;
	
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.server")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource dataSource() {
		DataSourceProperties properties = dataSourceProperties();
		HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
				.create(properties.getClassLoader())
				.driverClassName(properties.getDriverClassName())
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.type(HikariDataSource.class)
				.build();
		dataSource.setMaximumPoolSize(maxPoolSize);
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException{
		LocalContainerEntityManagerFactoryBean factroy = new LocalContainerEntityManagerFactoryBean();
		factroy.setDataSource(dataSource());
		factroy.setPackagesToScan(new String[] {"com.tistory.seungchul"});
		factroy.setJpaVendorAdapter(jpaVendorAdapter());
		factroy.setJpaProperties(jpaProperties());
		return factroy;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
		
	}
	
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.server.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.server.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.server.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.server.hibernate.format_sql"));
        if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.server.defaultSchema"))){
            properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.server.defaultSchema"));
        }
        return properties;
	}
	
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(emf);
		return tx;
	}
	
	@Bean
	public DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator(userData);
		populator.execute(dataSource());
		return populator;
	}
	
}
