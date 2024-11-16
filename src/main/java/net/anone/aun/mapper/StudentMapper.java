package net.anone.aun.mapper;

import net.anone.aun.dto.StudentDto;
import net.anone.aun.entity.Student;

public class StudentMapper {
	
	public static Student mapToStudent(StudentDto studentDto) {
		if (studentDto == null) {
			return null;
			
		}
		Student student = new Student(
				
				studentDto.getMatricNumber(),
				studentDto.getFirstName(),
				studentDto.getLastName(),
				studentDto.getDepartment(),
				studentDto.getReligion(),
				studentDto.getGender(),
				studentDto.getLevel()
				);
		return student;
		
	}
	
	public static StudentDto mapToStudentDto(Student student) {
		if (student == null) {
			return null;
			
		}
		StudentDto studentDto = new StudentDto(

				   student.getMatricNumber(),
				   student.getFirstName(),
				   student.getLastName(),
				   student.getDepartment(),
				   student.getReligion(),
				   student.getGender(),
				   student.getLevel()
				   );
		return studentDto;
	}

}
