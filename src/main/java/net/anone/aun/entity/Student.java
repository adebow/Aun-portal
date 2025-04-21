package net.anone.aun.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Students")
@Entity
public class Student {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String matricNumber;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String department;
	
	@Column
	private String religion;
	
	@Column
	private String gender;
	
	@Column
	private Long level;
	
	@Column
	private String password;
	

}
