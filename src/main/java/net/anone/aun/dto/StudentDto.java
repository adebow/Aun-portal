package net.anone.aun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentDto {
	
	private Long id;
	private String matricNumber;
	private String firstName;
	private String lastName;
	private String department;
	private String religion;
	private String gender;
	private Long level;
	private String password;

}