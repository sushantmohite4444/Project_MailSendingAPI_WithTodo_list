package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.User_Repository;
import com.example.demo.Entity.UserInfo;

@Service
public class UserService {
	
	@Autowired
	User_Repository ur;
	
	public UserInfo save(UserInfo u) {
		
		UserInfo ut= ur.save(u);
		
		return ut;

	}
	
	public UserInfo get(String email) {
		
		UserInfo u= ur.findBymail(email);
		
		return u;
		
	}
	
}
