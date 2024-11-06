package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Tasks;

@Repository
public interface Task_Repository extends JpaRepository<Tasks,Integer>{

}
