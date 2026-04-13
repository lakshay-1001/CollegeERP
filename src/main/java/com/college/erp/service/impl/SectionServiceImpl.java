package com.college.erp.service.impl;

import com.college.erp.entity.ClassEntity;
import com.college.erp.entity.Section;
import com.college.erp.repository.ClassRepository;
import com.college.erp.repository.SectionRepository;
import com.college.erp.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final ClassRepository classRepository;

    @Override
    public Section createSection(Section section) {

        ClassEntity classEntity = classRepository.findById(
                section.getClassEntity().getId()
        ).orElseThrow(() -> new RuntimeException("Class not found"));

        section.setClassEntity(classEntity);

        return sectionRepository.save(section);
    }

    @Override
    public List<Section> getSectionsByClass(Long classId) {
        return sectionRepository.findByClassEntityId(classId);
    }
}
