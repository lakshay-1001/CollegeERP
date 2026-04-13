package com.college.erp.controller;

import com.college.erp.entity.Attendance;
import com.college.erp.entity.enums.Role;
import com.college.erp.entity.enums.UserStatus;
import com.college.erp.security.RequireRole;
import com.college.erp.security.RequireStatus;
import com.college.erp.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // ✅ Mark Attendance
    @PostMapping
    @RequireRole({Role.ADMIN, Role.TEACHER})
    @RequireStatus({UserStatus.VERIFIED})
    public Attendance markAttendance(
            @RequestParam Long studentId,
            @RequestParam String date,
            @RequestParam Boolean present
    ) {
        return attendanceService.markAttendance(
                studentId,
                LocalDate.parse(date),
                present
        );
    }

    // ✅ Student-wise
    @GetMapping("/student/{studentId}")
    @RequireRole({Role.ADMIN, Role.TEACHER, Role.STUDENT})
    @RequireStatus({UserStatus.VERIFIED})
    public List<Attendance> getByStudent(@PathVariable Long studentId) {
        return attendanceService.getByStudent(studentId);
    }

    // ✅ Section-wise
    @GetMapping("/section/{sectionId}")
    @RequireRole({Role.ADMIN, Role.TEACHER})
    @RequireStatus({UserStatus.VERIFIED})
    public List<Attendance> getBySection(@PathVariable Long sectionId) {
        return attendanceService.getBySection(sectionId);
    }

    // ✅ Class-wise
    @GetMapping("/class/{classId}")
    @RequireRole({Role.ADMIN, Role.TEACHER})
    @RequireStatus({UserStatus.VERIFIED})
    public List<Attendance> getByClass(@PathVariable Long classId) {
        return attendanceService.getByClass(classId);
    }
}