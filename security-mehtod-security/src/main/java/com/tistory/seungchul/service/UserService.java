package com.tistory.seungchul.service;

import java.util.List;

import com.tistory.seungchul.model.UserEntity;

public interface UserService {

	public UserEntity getUserById(String userId);
	
	public List<UserEntity> getUserList();
}
