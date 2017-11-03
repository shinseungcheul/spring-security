package com.tistory.seungchul.configuration;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
@ComponentScan
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(ResourceServerConfig.class);

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "remote.server") 
	public Properties remoteProperties() {
		return new Properties();
	}
	
	
	@Bean
	public RemoteTokenServices RemoteTokenService() {
		RemoteTokenServices service = new RemoteTokenServices();
		Properties prop = remoteProperties();
		service.setCheckTokenEndpointUrl((String)prop.get("checkUrl"));
		service.setClientId((String)prop.get("clientId"));
		service.setClientSecret((String)prop.get("clientSecret")); 
//		service.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
//		service.setClientId("trust-client-first");
//		service.setClientSecret("1234");
		log.warn((String)prop.get("checkUrl")+":::"+(String)prop.get("clientId")+"::::"+(String)prop.get("clientSecret"));
		return service;
	}
	
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors()
			.and()
				.authorizeRequests()
					.antMatchers("/h2-console/**").permitAll()
					.antMatchers(HttpMethod.GET,"/**").access("#oauth2.hasScope('read')")
					.antMatchers(HttpMethod.POST,"/**").access("#oauth2.hasScope('write')")
					.antMatchers(HttpMethod.PUT,"/**").access("#oauth2.hasScope('write')")
					.antMatchers(HttpMethod.DELETE,"/**").access("#oauth2.hasScope('write')")
					.antMatchers(HttpMethod.OPTIONS,"/**").access("#oauth2.hasScope('write')");
	
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(RemoteTokenService()).stateless(false);
	}
	
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new  UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.addAllowedOrigin("*");
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.OPTIONS);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
}
