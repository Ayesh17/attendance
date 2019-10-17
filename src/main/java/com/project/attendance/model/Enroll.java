package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="enrolls")
@EntityListeners(AuditingEntityListener.class)
public class Enroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int userId;
    private String name;
    private String subject_code;
    private int year;
    private int semester;

    public Enroll() {
    }

    public Enroll(int userId, String name, String subject_code, int year, int semester) {
        this.userId = userId;
        this.name = name;
        this.subject_code = subject_code;
        this.year = year;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
