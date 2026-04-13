package com.college.erp.service;

import com.college.erp.entity.Section;

import java.util.List;

public interface SectionService {

    Section createSection(Section section);

    List<Section> getSectionsByClass(Long classId);
}