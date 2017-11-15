package com.tistory.seungchul.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tistory.seungchul.model.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findUserEntityByUserId(String userId);
	
	public UserEntity findUserEntityByUid(String uid);
	
}
