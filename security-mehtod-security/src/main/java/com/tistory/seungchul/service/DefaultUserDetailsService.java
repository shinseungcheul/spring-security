package com.tistory.seungchul.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tistory.seungchul.model.UserAuthority;
import com.tistory.seungchul.model.UserEntity;

@Service("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity user = userService.getUserById(userId);
		if(user ==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new User(user.getUserId(), user.getPassword(), true, true, true, true, getGrantedAuthories(user));
		
		
	}
	
	
	private List<GrantedAuthority> getGrantedAuthories(UserEntity user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for( UserAuthority auth : user.getUserAuthorities()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+auth.getUserRole()));
		}
		
		return authorities;
		
	}
	
	

}
