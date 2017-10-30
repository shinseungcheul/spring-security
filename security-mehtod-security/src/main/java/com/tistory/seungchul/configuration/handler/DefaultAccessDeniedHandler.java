package com.tistory.seungchul.configuration.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class DefaultAccessDeniedHandler implements AccessDeniedHandler {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("auth : " + auth);
		if(auth != null) {
			log.warn("User: " + auth.getName() 
            + " attempted to access the protected URL: "
            + request.getRequestURI());
			
			response.sendError(HttpStatus.UNAUTHORIZED.value(), accessDeniedException.getMessage());
		}
		
		
	}

}
