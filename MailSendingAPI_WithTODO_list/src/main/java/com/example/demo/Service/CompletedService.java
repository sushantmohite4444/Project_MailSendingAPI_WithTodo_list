package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.Completed_Repository;
import com.example.demo.Entity.Completed;

@Service
public class CompletedService {
	
	@Autowired
	Completed_Repository cr;
	
	public String delete(int id) {
		cr.deleteById(id);
		return "delete successfully";
	}
	
	
	public Completed backtotask(int id) {
		Completed tsdt = cr.getById(id);
		delete(id);
		return tsdt;
	}

}
