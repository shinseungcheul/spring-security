package com.tistory.seungchul.service.user;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.tistory.seungchul.model.user.UserEntity;

public interface UserService {

	public UserEntity findUserByUserId(String userId);
	
	public UserEntity findUserByOID(String oid);
	
	@Secured("ROLE_Admin")
	public List<UserEntity> getAllUser();
	
}
