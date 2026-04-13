package com.college.erp.repository;

import com.college.erp.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByExpiryDateAfter(LocalDateTime now);
}