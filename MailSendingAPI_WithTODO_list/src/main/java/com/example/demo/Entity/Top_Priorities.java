package com.example.demo.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Top_Priorities {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int TPid;
	private String Top_Priorities;
	
}
