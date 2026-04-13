package com.college.erp.repository;

import com.college.erp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByClassEntityId(Long classId);

    Optional<Student> findByUserId(Long userId); // 🔥 important
}