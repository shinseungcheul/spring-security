package com.tistory.seungchul.service.user;

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

import com.tistory.seungchul.model.user.UserAuthority;
import com.tistory.seungchul.model.user.UserEntity;

@Service("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user = userService.findUserByUserId(userId);
		if( user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return new User(user.getUserId(), user.getPassword(), getUserAuthority(user));
	}
	
	
	private List<GrantedAuthority> getUserAuthority(UserEntity user){
		List<GrantedAuthority> list = new ArrayList<>();
		for(UserAuthority auth : user.getUserAuthorities()) {
			list.add(new SimpleGrantedAuthority("ROLE_"+auth.getUserRole()));
		}
		return list ; 
		
	}

}
