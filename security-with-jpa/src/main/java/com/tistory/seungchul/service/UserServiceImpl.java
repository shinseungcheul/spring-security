package com.tistory.seungchul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.seungchul.model.UserEntity;
import com.tistory.seungchul.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserEntity getUserById(String userId) {
		
		return userRepository.findUserEntityByUserId(userId);
	}


	@Override
	public List<UserEntity> getUserList() {
		return userRepository.findAll();
	}

}
