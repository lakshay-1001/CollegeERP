package com.college.erp.service.impl;

import com.college.erp.entity.Notice;
import com.college.erp.entity.User;
import com.college.erp.repository.NoticeRepository;
import com.college.erp.repository.UserRepository;
import com.college.erp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public Notice createNotice(Notice notice, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        notice.setCreatedBy(user);
        notice.setCreatedAt(LocalDateTime.now());

        return noticeRepository.save(notice);
    }

    @Override
    public List<Notice> getAllActiveNotices() {
        return noticeRepository.findByExpiryDateAfter(LocalDateTime.now());
    }
}
