package com.tistory.seungchul.configuration.init;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class InitDataConfig {

	@Autowired
	Environment environment;
	
	@Autowired
	DataSource dataSource;
	
	@Value("classpath:schema/token-schema.sql")
	Resource tokenSchema;
	
	@Value("classpath:schema/user-data.sql")
	Resource userData;
	
	@Value("classpath:schema/client-details-data.sql")
	Resource clientDetailsData;
	
	
	@Bean
	public DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator data = new ResourceDatabasePopulator();
		data.addScript(tokenSchema);
		data.addScript(userData);
		data.addScript(clientDetailsData);
		data.execute(dataSource);
		return data;
	}
}
