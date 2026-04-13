package com.college.erp.service;

import com.college.erp.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getStudentsByClass(Long classId);

    Student getStudentByUserId(Long userId);

    Student completeStudentProfile(Long studentId, Student updatedData);
}