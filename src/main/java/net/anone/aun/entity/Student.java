package net.anone.aun.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Students")
@Entity
public class Student {
	 
	@Id
	private String matricNumber;
	
	@Column
	private String firstName;
	private String lastName;
	private String department;
	
	private String religion;
	private String gender;
	private Long level;
	

}
