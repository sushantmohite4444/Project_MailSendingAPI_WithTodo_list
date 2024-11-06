package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UserInfo;

import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface User_Repository extends JpaRepository<UserInfo, Integer> {

	UserInfo findBymail(String email);
	
			
	
}
