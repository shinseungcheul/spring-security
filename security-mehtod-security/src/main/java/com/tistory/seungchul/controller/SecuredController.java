package com.tistory.seungchul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.seungchul.service.SecuredService;

@RestController
@RequestMapping("/secured")
public class SecuredController {

	@Autowired
	SecuredService securedService;
	
	@GetMapping("/unsecured")
	public String unsecured() {
		return "unsecured method";
	}
	
	@GetMapping("/secureda")
	public String adminSecured() {
		System.out.println("secured admin");
		return securedService.admin();
	}
	
	@GetMapping("/secureduser")
	public String userSecured() {
		System.out.println("secured user");
		return securedService.user();
	}
	
	@GetMapping
	public String secured() {
		return "secured /";
	}
}
