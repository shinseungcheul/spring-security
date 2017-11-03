package com.tistory.seungchul.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	private static Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/admin")
	public String adminMain() {
		
		return "admin main";
	}
	
	@GetMapping("/user/{userId}")
	public String getUser(@PathVariable String userId) {
		return userId;
	}
	
	
	@PostMapping("/user")
	public Principal addUser(@RequestParam String userId, @RequestParam String userPw, Principal user) {
		System.out.println(userId + ":::::"+ userPw);
		return user;
	}
}
