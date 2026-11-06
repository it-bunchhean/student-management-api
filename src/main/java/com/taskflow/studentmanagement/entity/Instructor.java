package com.taskflow.studentmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kh_name", length = 50, nullable = false)
    private String khName;

    @Column(name = "en_name", length = 50, nullable = false)
    private String enName;

    @Column(name = "specialization", length = 50, nullable = false)
    private String specialization;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "specilization", nullable = false)
    private String specilization;

}
