package com.college.erp.controller;

import com.college.erp.entity.Student;
import com.college.erp.entity.enums.Role;
import com.college.erp.entity.enums.UserStatus;
import com.college.erp.security.RequireRole;
import com.college.erp.security.RequireStatus;
import com.college.erp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ✅ Create Student Profile (Admin only)
    @PostMapping
    @RequireRole({Role.ADMIN})
    @RequireStatus({UserStatus.VERIFIED})
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // ✅ Get Students by Class (Admin + Teacher)
    @GetMapping("/class/{classId}")
    @RequireRole({Role.ADMIN, Role.TEACHER})
    @RequireStatus({UserStatus.VERIFIED})
    public List<Student> getStudentsByClass(@PathVariable Long classId) {
        return studentService.getStudentsByClass(classId);
    }

    // ✅ Get Student by User ID (useful for profile)
    @GetMapping("/user/{userId}")
    @RequireRole({Role.ADMIN, Role.STUDENT})
    @RequireStatus({UserStatus.VERIFIED})
    public Student getStudentByUser(@PathVariable Long userId) {
        return studentService.getStudentByUserId(userId);
    }

    // ✅ Complete Student Profile (ADMIN only)
    @PutMapping("/{studentId}/complete")
    @RequireRole({Role.ADMIN})
    @RequireStatus({UserStatus.VERIFIED})
    public Student completeProfile(
            @PathVariable Long studentId,
            @RequestBody Student student
    ) {
        return studentService.completeStudentProfile(studentId, student);
    }
}