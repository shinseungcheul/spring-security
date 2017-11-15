package com.tistory.seungchul.controller.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.seungchul.model.user.UserEntity;
import com.tistory.seungchul.service.user.UserService;

@RestController
@RequestMapping("/join")
public class JoinController {

	@Autowired
	UserService userService;
	
	@GetMapping("/exist/{userId}")
	public boolean existUser(@PathVariable String userId) {
		System.out.println(userId);
		UserEntity user = userService.findUserByUserId(userId);
		if(user != null) {
			return true;
		}
		return false;
	}
}
