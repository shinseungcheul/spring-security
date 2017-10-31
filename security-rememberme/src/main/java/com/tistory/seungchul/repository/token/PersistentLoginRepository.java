package com.tistory.seungchul.repository.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.seungchul.model.user.PersistentLogin;


@Repository
@Transactional
public class PersistentLoginRepository implements PersistentTokenRepository {
	
	@Autowired
	@Qualifier("rememberTokenRepository")
	TokenRepository tokenRepository;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// TODO Auto-generated method stub
		PersistentLogin exsistLogin = tokenRepository.findByUsername(token.getUsername());
		PersistentLogin login = new PersistentLogin();
		login.setSeries(token.getSeries());
		login.setToken(token.getTokenValue());
		login.setUsername(token.getUsername());
		login.setLast_used(token.getDate());
		if(exsistLogin ==null) {
			tokenRepository.save(login); 
		}else {
			System.out.println(token.getSeries()+"::"+token.getTokenValue()+"::"+token.getDate());
			updateToken(token.getSeries(), token.getTokenValue(), token.getDate());
		}

	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		PersistentLogin login = tokenRepository.findBySeries(series);
		login.setLast_used(lastUsed);
		login.setToken(tokenValue);
		tokenRepository.saveAndFlush(login);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		PersistentLogin login = tokenRepository.findBySeries(seriesId);
		if(login !=null) {
			return new PersistentRememberMeToken(login.getUsername(), login.getSeries(), login.getToken(), login.getLast_used());
		}
		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		// TODO Auto-generated method stub
		PersistentLogin login = tokenRepository.findByUsername(username);
		if(login != null) {
			tokenRepository.delete(login);
		}

	}

}
