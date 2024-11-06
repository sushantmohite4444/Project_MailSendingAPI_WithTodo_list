package com.example.demo.Entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Tid;
	@Column(unique = true)
	private String Task;
	
	
	
	@OneToMany(targetEntity=Top_Priorities.class,cascade = CascadeType.ALL, 
            fetch = FetchType.EAGER)
	@JoinColumn(name="ttopp_uk" ,referencedColumnName = "Tid" )
	private List<Top_Priorities> topprio;



	
	
	

}
