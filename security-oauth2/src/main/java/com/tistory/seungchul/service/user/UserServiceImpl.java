package com.tistory.seungchul.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public UserEntity findUserByOID(String oid) {
		return userRepository.findUserEntityByOid(oid);
	}

	@Override
	public List<UserEntity> getAllUser() {
		return userRepository.findAll();
	}

}
