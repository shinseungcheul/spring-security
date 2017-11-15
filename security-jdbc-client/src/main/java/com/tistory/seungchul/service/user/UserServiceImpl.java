package com.tistory.seungchul.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.seungchul.model.user.UserEntity;
import com.tistory.seungchul.repository.user.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserEntity findUserByUserId(String userId) {
		return userRepository.findUserEntityByUserId(userId);
	}

	@Override
	public UserEntity findUserByUID(String uid) {
		return userRepository.findUserEntityByUid(uid);
	}

	@Override
	public List<UserEntity> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public void addUser(UserEntity user) {
		userRepository.save(user);
	}

	@Override
	@Secured("ROLE_Admin")
	public void removeUser(String userId) {
		UserEntity user = findUserByUserId(userId);
		if(user !=null) {
			userRepository.delete(user);
		}
	}

	
	
}
