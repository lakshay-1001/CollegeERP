package com.college.erp.service;

import com.college.erp.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(Long studentId, LocalDate date, Boolean present);

    List<Attendance> getByStudent(Long studentId);

    List<Attendance> getBySection(Long sectionId);

    List<Attendance> getByClass(Long classId);
}