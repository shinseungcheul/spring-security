package com.tistory.seungchul.controller;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.seungchul.model.user.UserEntity;
import com.tistory.seungchul.service.user.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private AtomicInteger usedCount = new AtomicInteger();
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class); 
	
	@Autowired
	UserService userService;
	
	
	@GetMapping(path = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<UserEntity> userList(Principal principal){
		int count = usedCount.incrementAndGet();
		List<UserEntity> userList = userService.getAllUser();
		
		log.info("usedCount : " +count +" ::: username : " +principal.getName() );
		return userList;
		
	}
	
}
