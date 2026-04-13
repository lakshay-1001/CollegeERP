package com.college.erp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    private String rollNumber;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = true)
    private ClassEntity classEntity;
}
