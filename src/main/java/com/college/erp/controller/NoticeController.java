package com.college.erp.controller;

import com.college.erp.entity.Notice;
import com.college.erp.entity.enums.Role;
import com.college.erp.entity.enums.UserStatus;
import com.college.erp.security.JwtUtil;
import com.college.erp.security.RequireRole;
import com.college.erp.security.RequireStatus;
import com.college.erp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final JwtUtil jwtUtil;

    // ✅ Create Notice (Admin / Teacher)
    @PostMapping
    @RequireRole({Role.ADMIN, Role.TEACHER})
    @RequireStatus({UserStatus.VERIFIED})
    public Notice create(
            @RequestBody Notice notice,
            @RequestHeader("Authorization") String token
    ) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return noticeService.createNotice(notice, email);
    }

    // ✅ Get All Notices (Everyone)
    @GetMapping
    public List<Notice> getAll() {
        return noticeService.getAllActiveNotices();
    }
}
