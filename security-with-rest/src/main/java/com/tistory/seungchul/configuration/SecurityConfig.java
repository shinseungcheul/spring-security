package com.tistory.seungchul.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tistory.seungchul.configuration.auth.CustomAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String REALM = "security-with-rest";
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("1234").roles("User","Admin")
			.and()
			.withUser("user").password("1234").roles("User");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(HttpMethod.OPTIONS,"/**")
			.antMatchers("/h2-console/**");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and().csrf().disable()
			.requestMatchers()
				.antMatchers("/**")
			.and()
			.authorizeRequests()
				.antMatchers("/user/**").hasAnyRole("User","Admin")
				.antMatchers("/admin").hasRole("Admin")
			.and()
			.httpBasic()
				.realmName(REALM)
				.authenticationEntryPoint(getAuthenticationEntryPoint());
	}
	
	
	@Bean
	public CustomAuthenticationEntryPoint getAuthenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint(REALM);
	}
	
	
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		config.addAllowedMethod(HttpMethod.OPTIONS);
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setAllowCredentials(true);
		config.setMaxAge(60*60L);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
