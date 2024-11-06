package com.example.demo.Dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Completed;

@Repository
public interface Completed_Repository extends JpaRepositoryImplementation<Completed, Integer>{

}
