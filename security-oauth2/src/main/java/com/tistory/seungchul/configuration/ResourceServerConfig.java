package com.tistory.seungchul.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_SERVER_ID = "resource_server";
	
	 @Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		 resources.resourceId(RESOURCE_SERVER_ID).stateless(false);
	 }
	 
	 @Override
	public void configure(HttpSecurity http) throws Exception {
		 http
		 	.anonymous().disable()
		 	.requestMatchers()
		 		.antMatchers("/**")
		 	.and()
		 	.authorizeRequests()
		 		.antMatchers("/user/**").hasAnyRole("Admin","User")
		 		.antMatchers("/admin/**").hasRole("Admin")
		 		.antMatchers("/token/**").hasRole("Admin")
		 	.and()
		 	.exceptionHandling()
		 		.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	 
	 }
}
