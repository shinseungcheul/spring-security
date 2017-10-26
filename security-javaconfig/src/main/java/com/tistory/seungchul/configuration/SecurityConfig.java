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


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin").password("1234").roles("USER","ADMIN")
				.and()
				.withUser("user").password("1234").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf().disable()
			.requestMatchers().antMatchers("/**")
			.and()
			.authorizeRequests()
				.antMatchers("/user/test/**").permitAll()
				.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
			.and()
			.formLogin()
//				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/user")
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
			.and()
			.exceptionHandling().accessDeniedPage("/login");
		
	
	}
	

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring().antMatchers(HttpMethod.OPTIONS,"/**")
			.antMatchers("/h2-console/**");
	
	}
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
	
	
}
