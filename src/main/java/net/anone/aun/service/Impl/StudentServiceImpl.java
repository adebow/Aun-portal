package net.anone.aun.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.anone.aun.dto.StudentDto;
import net.anone.aun.entity.Student;
import net.anone.aun.exception.StudentNotFoundException;
import net.anone.aun.mapper.StudentMapper;
import net.anone.aun.repository.StudentRepository;
import net.anone.aun.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	private StudentRepository studentRepository;
	private final AtomicInteger counter = new AtomicInteger(1000);              
	private PasswordEncoder passwordEncoder;
	
	public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
		this.studentRepository = studentRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public StudentDto registerStudent(StudentDto studentDto) {
		
		if (studentDto == null) {
			logger.error("StudentDto is null");
			throw new IllegalArgumentException("StudentDto must not be null");
			
		}
		
		String uniqueMatricNumber = generateMatricNumber(studentDto.getDepartment());
		
		Student student = StudentMapper.mapStudent(studentDto);
		student.setMatricNumber(uniqueMatricNumber);
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		
		Student savedStudent = studentRepository.save(student);
		logger.info("student registered with matric number : {}", uniqueMatricNumber);
		
		return StudentMapper.mapStudentDto(savedStudent);
	}
	
	private String generateMatricNumber(String department) {
		
		String acronym = getDepartmentAcronym(department);
		String year = String.valueOf(LocalDate.now().getYear());
		String baseMatricNumber = year + acronym;
		
		int currentCount = counter.getAndIncrement();
		String uniqueMatricNumber = baseMatricNumber + currentCount;
		
		while(studentRepository.existsByMatricNumber(uniqueMatricNumber)) {
			currentCount++;
			uniqueMatricNumber = baseMatricNumber + currentCount;
		}
	
		return uniqueMatricNumber;
		
	}
	
	private String getDepartmentAcronym(String department) {
		
		if(department == null || department.isEmpty()) {
			logger.error("Department is null");
			throw new IllegalArgumentException("Department must not be null");
		}
		
		String[] nameParts = department.trim().split("[\\s-]+");
		StringBuilder acronym = new StringBuilder();
		
		for(String part : nameParts) {
			if(!part.isEmpty()) {
				acronym.append(part.charAt(0));
			}
		}
		return acronym.toString().toUpperCase();
		
	}


	@Transactional
	@Override
	public String deleteStudent(String matricNumber) {
		
	Student student = studentRepository
				.findByMatricNumber( matricNumber)
				.orElseThrow(() -> {
					logger.error("Student with matric number{} not found", matricNumber);
				    return new StudentNotFoundException("Student with matric number" + matricNumber + "does not exist");
				});
		studentRepository.deleteByMatricNumber( matricNumber);
		logger.info("Student with matric number {} deleted" , matricNumber);
		return "Student with matric number" + matricNumber + "deleted successfully.";
	}



	@Override
	public StudentDto getStudent(String matricNumber) {
		Student student = studentRepository
				.findByMatricNumber(matricNumber)
				.orElseThrow(() -> new StudentNotFoundException("Student with matric number" + matricNumber + "does not exist"));
		
		logger.info("Fetched student with matric number{}", matricNumber);
		return StudentMapper.mapStudentDto(student);
	}



	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		logger.info("Fetched all students, count: {}", students.size());
		return students.stream()
				.map((student) -> StudentMapper.mapStudentDto(student))
				       .collect(Collectors.toList());
	}



	@Transactional
	@Override
	public StudentDto updateStudent(String matricNumber, StudentDto studentDto) {
		if (studentDto == null) {
			logger.error("StudentDto is null");
			throw new IllegalArgumentException("StudentDto must not be null");
			
		}
		
		Student student = studentRepository
				.findByMatricNumber(matricNumber)
				.orElseThrow(() ->{
					logger.error("Student with matric number{} not found", matricNumber);
					return new StudentNotFoundException("Student with matric number" + matricNumber + "does not exist");
				});
		
		if(studentDto.getFirstName() !=null) {
		student.setFirstName(studentDto.getFirstName());
		}
		if(studentDto.getLastName() !=null) {
		student.setLastName(studentDto.getLastName());
		}
		if(studentDto.getDepartment() !=null) {
		student.setDepartment(studentDto.getDepartment());
		}
		if(studentDto.getReligion() !=null) {
		student.setReligion(studentDto.getReligion());
		}
		if(studentDto.getGender() !=null) {
		student.setGender(studentDto.getGender());
		}
		if(studentDto.getLevel() !=null) {
		student.setLevel(studentDto.getLevel());
		}
		
		Student updatedStudent = studentRepository.save(student);
		
		logger.info( "Updated student with matric number: {}", matricNumber);
	
		return StudentMapper.mapStudentDto(updatedStudent);
	}
	

}