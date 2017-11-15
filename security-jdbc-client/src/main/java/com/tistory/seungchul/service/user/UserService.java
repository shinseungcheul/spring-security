package com.tistory.seungchul.service.user;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.tistory.seungchul.model.user.UserEntity;

public interface UserService {

	public UserEntity findUserByUserId(String userId);
	
	public UserEntity findUserByUID(String oid);
	
	public List<UserEntity> getAllUser();
	
	public void addUser(UserEntity user);
	
	public void removeUser(String userId);
	
}
