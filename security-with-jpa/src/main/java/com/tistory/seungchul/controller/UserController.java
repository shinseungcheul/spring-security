package com.tistory.seungchul.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.seungchul.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@GetMapping
	public String userDefault(Principal principal) {
		System.out.println(principal);
		return principal.toString();
	}
	
	@GetMapping("/test")
	public String userControllerTest() {
		return "unsecured user controller test";
	}
	
}
