package net.anone.aun.service.Impl;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.anone.aun.entity.Student;
import net.anone.aun.repository.StudentRepository;

@Service
public class StudentUserDetailsService implements UserDetailsService {
	
	private final StudentRepository studentRepository;
	
	public StudentUserDetailsService(StudentRepository studentRepository) {
		
		this.studentRepository = studentRepository;
		
	}

	@Override
	public UserDetails loadUserByUsername(String matricNumber) throws UsernameNotFoundException {
		 Student student = studentRepository.findByMatricNumber(matricNumber)
				 .orElseThrow(() -> new UsernameNotFoundException("Student not found"));
		 
		return new User(student.getMatricNumber(), student.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT")));
	}

}
