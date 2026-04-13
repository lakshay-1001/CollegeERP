package com.college.erp.repository;

import com.college.erp.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByStudentSectionId(Long sectionId);

    List<Attendance> findByStudentClassEntityId(Long classId);

    boolean existsByStudentIdAndDate(Long studentId, LocalDate date);
}