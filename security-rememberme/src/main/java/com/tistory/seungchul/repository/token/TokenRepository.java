package com.tistory.seungchul.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tistory.seungchul.model.user.PersistentLogin;


@Repository("rememberTokenRepository")
public interface TokenRepository extends JpaRepository<PersistentLogin, Long> {

	public PersistentLogin findBySeries(String series);
	
	public PersistentLogin findByUsername(String username);
}
