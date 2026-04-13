package com.college.erp.controller;

import com.college.erp.entity.Section;
import com.college.erp.entity.enums.Role;
import com.college.erp.entity.enums.UserStatus;
import com.college.erp.security.RequireRole;
import com.college.erp.security.RequireStatus;
import com.college.erp.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    // ✅ Create Section (ADMIN only)
    @PostMapping
    @RequireRole({Role.ADMIN})
    @RequireStatus({UserStatus.VERIFIED})
    public Section create(@RequestBody Section section) {
        return sectionService.createSection(section);
    }

    // ✅ Get Sections by Class
    @GetMapping("/class/{classId}")
    @RequireRole({Role.ADMIN, Role.TEACHER})
    @RequireStatus({UserStatus.VERIFIED})
    public List<Section> getByClass(@PathVariable Long classId) {
        return sectionService.getSectionsByClass(classId);
    }
}
