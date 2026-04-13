package com.college.erp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sections",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "class_id"})) // 🔥 no duplicate A in same class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // A, B, C

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private ClassEntity classEntity;
}