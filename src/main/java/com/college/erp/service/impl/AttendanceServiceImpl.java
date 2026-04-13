package com.college.erp.service.impl;

import com.college.erp.entity.Attendance;
import com.college.erp.entity.Student;
import com.college.erp.repository.AttendanceRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Override
    public Attendance markAttendance(Long studentId, LocalDate date, Boolean present) {

        // ❌ Prevent duplicate
        if (attendanceRepository.existsByStudentIdAndDate(studentId, date)) {
            throw new RuntimeException("Attendance already marked for this date");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Attendance attendance = Attendance.builder()
                .student(student)
                .date(date)
                .present(present)
                .build();

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Override
    public List<Attendance> getBySection(Long sectionId) {
        return attendanceRepository.findByStudentSectionId(sectionId);
    }

    @Override
    public List<Attendance> getByClass(Long classId) {
        return attendanceRepository.findByStudentClassEntityId(classId);
    }
}