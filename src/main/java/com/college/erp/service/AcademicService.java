package com.college.erp.service;

import com.college.erp.entity.ClassEntity;
import com.college.erp.entity.ClassSubject;
import com.college.erp.entity.Subject;

public interface AcademicService {

    ClassEntity createClass(ClassEntity classEntity);

    Subject createSubject(Subject subject);

    ClassSubject assignSubjectToClass(ClassSubject cs);
}
