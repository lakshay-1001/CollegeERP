package com.college.erp.service.impl;

import com.college.erp.entity.ClassEntity;
import com.college.erp.entity.Section;
import com.college.erp.entity.Student;
import com.college.erp.entity.User;
import com.college.erp.repository.ClassRepository;
import com.college.erp.repository.SectionRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.repository.UserRepository;
import com.college.erp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final SectionRepository sectionRepository;

    @Override
    public Student createStudent(Student student) {

        // ✅ Fetch User
        User user = userRepository.findById(student.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Ensure role is STUDENT
        if (!user.getRole().name().equals("STUDENT")) {
            throw new RuntimeException("User is not a STUDENT");
        }

        // ✅ Prevent duplicate student profile
        studentRepository.findByUserId(user.getId()).ifPresent(s -> {
            throw new RuntimeException("Student profile already exists for this user");
        });

        // ✅ Fetch Class
        ClassEntity classEntity = classRepository.findById(
                student.getClassEntity().getId()
        ).orElseThrow(() -> new RuntimeException("Class not found"));

        // ✅ Set relations
        student.setUser(user);
        student.setClassEntity(classEntity);

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByClass(Long classId) {
        return studentRepository.findByClassEntityId(classId);
    }

    @Override
    public Student getStudentByUserId(Long userId) {
        return studentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Student completeStudentProfile(Long studentId, Student updatedData) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        ClassEntity classEntity = classRepository.findById(
                updatedData.getClassEntity().getId()
        ).orElseThrow(() -> new RuntimeException("Class not found"));

        // ✅ FETCH SECTION PROPERLY
        Section section = sectionRepository.findById(
                updatedData.getSection().getId()
        ).orElseThrow(() -> new RuntimeException("Section not found"));

        student.setClassEntity(classEntity);
        student.setSection(section);
        student.setRollNumber(updatedData.getRollNumber());
        student.setPhone(updatedData.getPhone());

        return studentRepository.save(student);
    }
}