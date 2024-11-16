package net.anone.aun.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
	
	private String matricNumber;
	private String firstName;
	private String lastName;
	private String department;
	private String religion;
	private String gender;
	private Long level;

}