package com.tistory.seungchul.configuration.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	
	public CustomAuthenticationEntryPoint(String realmName) {
		// TODO Auto-generated constructor stub
		this.setRealmName(realmName);
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.addHeader("WWW-Authenticate","Basic realm=" + getRealmName() + "");
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 : " + authException.getMessage());
	}
}
