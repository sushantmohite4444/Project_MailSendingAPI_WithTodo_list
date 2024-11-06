package com.example.demo.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
		name ="UserInfo", 
        uniqueConstraints=
            @UniqueConstraint(columnNames ={"mail"})
    )
@Component
@EntityScan
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int userid;
	
	@Column(nullable = false,unique = true)
	private String mail;
	@Column(nullable = false)
	private String password;
	
	private String name ="User";
	private String last_name="User";
	
	@Lob
	private byte[] image;
	
	 @OneToMany(targetEntity=Completed.class,cascade = CascadeType.ALL, 
             fetch = FetchType.EAGER )
	 @JoinColumn(name="uc_fk", referencedColumnName ="userid")
	 private List<Completed> comp;

	@OneToMany(targetEntity=Tasks.class,cascade = CascadeType.ALL, 
            fetch = FetchType.EAGER )
	@JoinColumn(name="ut_fk", referencedColumnName ="userid")
	private List<Tasks> tasksm;



	



	



	



	


	


	
	
	
	
	
	

}
