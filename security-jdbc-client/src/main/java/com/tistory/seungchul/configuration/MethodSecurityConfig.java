package com.tistory.seungchul.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.tistory.seungchul.constant.RoleConstant;


@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		proxyTargetClass = true,
		securedEnabled = true
)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	public static String seperate = ">";

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
		handler.setRoleHierarchy(roleHierarchy());
		return handler;
	}
	
	
	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl role = new RoleHierarchyImpl();
		role.setHierarchy(RoleConstant.ADMIN + seperate + RoleConstant.USER + seperate + RoleConstant.GEUST);
		return role;
	}
	
	
	@Bean
    public RoleHierarchyVoter roleVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

}
