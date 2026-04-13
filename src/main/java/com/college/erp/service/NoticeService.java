package com.college.erp.service;

import com.college.erp.entity.Notice;

import java.util.List;

public interface NoticeService {

    Notice createNotice(Notice notice, String email);

    List<Notice> getAllActiveNotices();
}
