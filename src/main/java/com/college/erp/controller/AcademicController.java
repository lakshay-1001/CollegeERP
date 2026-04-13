package com.college.erp.controller;

import com.college.erp.entity.ClassEntity;
import com.college.erp.entity.ClassSubject;
import com.college.erp.entity.Subject;
import com.college.erp.entity.enums.Role;
import com.college.erp.entity.enums.UserStatus;
import com.college.erp.security.RequireRole;
import com.college.erp.security.RequireStatus;
import com.college.erp.service.AcademicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/academic")
@RequiredArgsConstructor
public class AcademicController {

    private final AcademicService academicService;

    // ✅ Only ADMIN can create class
    @PostMapping("/class")
    @RequireRole({Role.ADMIN})
    @RequireStatus({UserStatus.VERIFIED})
    public ClassEntity createClass(@RequestBody ClassEntity classEntity) {
        return academicService.createClass(classEntity);
    }

    // ✅ ADMIN + TEACHER
    @PostMapping("/subject")
    @RequireRole({Role.ADMIN, Role.TEACHER})
    @RequireStatus({UserStatus.VERIFIED})
    public Subject createSubject(@RequestBody Subject subject) {
        return academicService.createSubject(subject);
    }

    // ✅ Assign subject to class
    @PostMapping("/assign")
    @RequireRole({Role.ADMIN})
    @RequireStatus({UserStatus.VERIFIED})
    public ClassSubject assign(@RequestBody ClassSubject cs) {
        return academicService.assignSubjectToClass(cs);
    }
}