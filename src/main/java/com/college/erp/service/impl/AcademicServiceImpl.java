package com.college.erp.service.impl;

import com.college.erp.entity.ClassEntity;
import com.college.erp.entity.ClassSubject;
import com.college.erp.entity.Subject;
import com.college.erp.entity.Teacher;
import com.college.erp.repository.ClassRepository;
import com.college.erp.repository.ClassSubjectRepository;
import com.college.erp.repository.SubjectRepository;
import com.college.erp.repository.TeacherRepository;
import com.college.erp.service.AcademicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademicServiceImpl implements AcademicService {

    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ClassEntity createClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public ClassSubject assignSubjectToClass(ClassSubject cs) {

        ClassEntity classEntity = classRepository.findById(cs.getClassEntity().getId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Subject subject = subjectRepository.findById(cs.getSubject().getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Teacher teacher = teacherRepository.findById(cs.getTeacher().getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        ClassSubject newCs = ClassSubject.builder()
                .classEntity(classEntity)
                .subject(subject)
                .teacher(teacher)
                .build();

        return classSubjectRepository.save(newCs);
    }
}
