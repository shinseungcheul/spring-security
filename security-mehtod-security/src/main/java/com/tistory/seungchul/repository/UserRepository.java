package com.tistory.seungchul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.seungchul.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findUserEntityByUserId(String userId);
}
