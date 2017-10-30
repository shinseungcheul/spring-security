package com.tistory.seungchul.service;

import org.springframework.stereotype.Service;

@Service("securedService")
public class SecuredServiceImpl implements SecuredService {

	@Override
	public String admin() {
		// TODO Auto-generated method stub
		return "admin secured";
	}

	@Override
	public String user() {
		// TODO Auto-generated method stub
		return "user secured";
	}

}
