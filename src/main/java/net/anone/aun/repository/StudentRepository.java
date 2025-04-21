package net.anone.aun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.anone.aun.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	boolean existsByMatricNumber(String uniqueMatricNumber);

	Optional<Student> findByMatricNumber(String matricNumber);

	String deleteByMatricNumber(String matricNumber);

}
