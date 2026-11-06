package com.taskflow.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_students_address")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_address_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String district;

    @Column(nullable = false, length = 50)
    private String country;
}
