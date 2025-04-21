package net.anone.aun.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.anone.aun.dto.StudentDto;
import net.anone.aun.service.StudentService;

@RestController
@RequestMapping("/api/students_portal")
public class StudentController {
	
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto){
		return new ResponseEntity<>(studentService.registerStudent(studentDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{matricNumber}")
	public ResponseEntity<String> deleteStudent(@PathVariable String matricNumber) {
		studentService.deleteStudent(matricNumber);
		return ResponseEntity.ok("Student deleted successfully");
		
	}
	
	@GetMapping("/{matricNumber}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable String matricNumber) {
		StudentDto studentDto = studentService.getStudent(matricNumber);
		return ResponseEntity.ok(studentDto);
	}
	
	@GetMapping("/get_all_students")
	public ResponseEntity<List<StudentDto>> getAllStudents() {
		List<StudentDto> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}
	
	@PutMapping("/{matricNumber}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable String matricNumber, @RequestBody StudentDto studentDto){
		StudentDto updatedStudent = studentService.updateStudent(matricNumber, studentDto);
		return ResponseEntity.ok(updatedStudent);
		
	}
	
	@GetMapping("/test-endpoint")
	public ResponseEntity<String> testEndpoint() {
	    return ResponseEntity.ok("Test endpoint works!");
	}

}
