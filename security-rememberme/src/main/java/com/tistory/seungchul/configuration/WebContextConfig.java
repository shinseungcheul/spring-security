package com.tistory.seungchul.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = "com.tistory.seungchul",
		includeFilters = @Filter({Controller.class,ControllerAdvice.class})
)
public class WebContextConfig extends WebMvcConfigurerAdapter {

}
