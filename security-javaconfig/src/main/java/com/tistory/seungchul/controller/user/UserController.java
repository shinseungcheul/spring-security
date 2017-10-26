package com.tistory.seungchul.controller.user;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("{id}")
	public String userId(@PathVariable String id) {
		return id;
	}
	
	
	@GetMapping
	public Principal user(Principal principal) {
		return principal;
	}
	
	
	
	@GetMapping("/test/url/result")
	public String urlTest() {
		return "urlTestResult";
	}
}
