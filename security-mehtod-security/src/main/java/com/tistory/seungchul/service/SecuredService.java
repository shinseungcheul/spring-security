package com.tistory.seungchul.service;

import org.springframework.security.access.annotation.Secured;

public interface SecuredService {

	@Secured("ROLE_Admin")
	public String admin() ;
	
	@Secured("ROLE_User")
	public String user();
	
}
