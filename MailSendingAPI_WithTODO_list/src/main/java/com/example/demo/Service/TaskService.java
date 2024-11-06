package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.Task_Repository;
import com.example.demo.Entity.Tasks;

@Service
public class TaskService {
	@Autowired
	Task_Repository tr;

	public Tasks delete(int id) {
		Tasks tsdt = tr.getById(id);
		tr.deleteById(id);
		return tsdt;
	}

}
