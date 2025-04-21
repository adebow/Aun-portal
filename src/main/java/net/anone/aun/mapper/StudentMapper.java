package net.anone.aun.mapper;

import net.anone.aun.dto.StudentDto;
import net.anone.aun.entity.Student;

public class StudentMapper {
	
	public static StudentDto mapStudentDto(Student student) {
		if (student == null) {
			return null;
			
		}
		return StudentDto.builder()
				.id(student.getId())
				.matricNumber(student.getMatricNumber())
				.firstName(student.getFirstName())
				.lastName(student.getLastName())
				.department(student.getDepartment())
				.religion(student.getReligion())
				.gender(student.getGender())
				.level(student.getLevel())
				.password(student.getPassword())
				.build();
	}
	
	public static Student mapStudent(StudentDto studentDto) {
		if(studentDto == null) {
			return null;
		}
		return Student.builder()
				.id(studentDto.getId())
				.matricNumber(studentDto.getMatricNumber())
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.department(studentDto.getDepartment())
				.religion(studentDto.getReligion())
				.gender(studentDto.getGender())
				.level(studentDto.getLevel())
				.password(studentDto.getPassword())
				.build();
		
	}

}
