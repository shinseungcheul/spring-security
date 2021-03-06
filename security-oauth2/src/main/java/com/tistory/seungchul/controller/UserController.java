package com.tistory.seungchul.controller;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.seungchul.model.user.UserEntity;
import com.tistory.seungchul.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final AtomicInteger count = new AtomicInteger();
	
	@Autowired
	UserService userService;

	@GetMapping("{userId}")
	public ResponseEntity<UserEntity> getUser(@PathVariable String userId) {
		UserEntity user = userService.findUserByUserId(userId);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public void addUser(@RequestBody UserEntity user) {
		user.setOid(UUID.randomUUID().toString());
		System.out.println(user);
		userService.addUser(user);
	}
	
	@DeleteMapping("/remove/{userId}")
	public void removeUser(@PathVariable String userId) {
		userService.removeUser(userId);
		System.out.println("removeUserId : "+userId);
	}
	
}
