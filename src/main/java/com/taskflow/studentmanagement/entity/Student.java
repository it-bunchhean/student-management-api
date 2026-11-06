package com.taskflow.studentmanagement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_code", unique = true , nullable = false, updatable = false)
    private String code;

    @Column(name = "kh_first_name", nullable = false)
    private String khFirstName;

    @Column(name = "kh_last_name", nullable = false)
    private String khLastName;

    @Column(name = "en_first_name", nullable = false)
    private String enFirstName;

    @Column(name = "en_last_name", nullable = false)
    private String enLastName;

    @Column(name = "date_of_birth", nullable = false, length = 10)
    private String dob;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phone;

    @Column(name = "gender", nullable = false, length = 7)
    private String gender;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentAddress address;

    private boolean deleted = false;
}
