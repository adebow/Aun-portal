package net.anone.aun.service;

import java.util.List;

import net.anone.aun.dto.StudentDto;

public interface StudentService {

	StudentDto registerStudent(StudentDto studentDto);
	
	StudentDto getStudent(String matricNumber);
	
	List<StudentDto>getAllStudents();
	
	String deleteStudent(String matricNumber);
	
	StudentDto updateStudent(String matricNumber, StudentDto studentDto);
}
