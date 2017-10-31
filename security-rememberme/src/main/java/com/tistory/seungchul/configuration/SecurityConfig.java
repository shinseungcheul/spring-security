package com.tistory.seungchul.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tistory.seungchul.configuration.handler.SecurityAccessDeniedHandler;
import com.tistory.seungchul.repository.token.PersistentLoginRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	PersistentLoginRepository persistentLoginRepository;
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf();
		
		http
			.requestMatchers()
				.antMatchers("/**")
			.and()
			.authorizeRequests()
				.antMatchers("/user/**").hasAnyRole("User","Admin")
				.antMatchers("/admin/**").hasRole("Admin")
				.antMatchers("/rememberme").hasRole("Admin")
			.and()
			.formLogin()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
			.and()
			.logout()
				.logoutSuccessUrl("/")
				.logoutUrl("/logout")
			.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler());
		
		http
			.rememberMe()
			.rememberMeParameter("remember-me")
			.tokenRepository(persistentLoginRepository)
			;
	
	}
	
//	@Bean
//	public PersistentTokenRepository tokenRepository() {
//		JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
//		token.setDataSource(dataSource);
//		return token;
//	}
	
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new SecurityAccessDeniedHandler();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
			.antMatchers("/h2-console/**");
	}
	
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.addAllowedOrigin("*");
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedMethod(HttpMethod.OPTIONS);
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	
	
	
	
}
