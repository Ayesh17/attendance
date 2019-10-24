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
    private String subject_code1;
    private String subject_code2;
    private String subject_code3;
    private String subject_code4;
    private String subject_code5;
    private String subject_code6;
    private String subject_code7;
    private String subject_code8;
    private String subject_code9;
    private String subject_code10;
    private String subject_code11;
    private String subject_code12;
    private int year;
    private int semester;

    public Enroll() {
    }

    public Enroll(int userId, String name, String subject_code1, String subject_code2, String subject_code3, String subject_code4, String subject_code5, String subject_code6, String subject_code7, String subject_code8, String subject_code9, String subject_code10, String subject_code11, String subject_code12, int year, int semester) {
        this.userId = userId;
        this.name = name;
        this.subject_code1 = subject_code1;
        this.subject_code2 = subject_code2;
        this.subject_code3 = subject_code3;
        this.subject_code4 = subject_code4;
        this.subject_code5 = subject_code5;
        this.subject_code6 = subject_code6;
        this.subject_code7 = subject_code7;
        this.subject_code8 = subject_code8;
        this.subject_code9 = subject_code9;
        this.subject_code10 = subject_code10;
        this.subject_code11 = subject_code11;
        this.subject_code12 = subject_code12;
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

    public String getSubject_code1() {
        return subject_code1;
    }

    public void setSubject_code1(String subject_code1) {
        this.subject_code1 = subject_code1;
    }

    public String getSubject_code2() {
        return subject_code2;
    }

    public void setSubject_code2(String subject_code2) {
        this.subject_code2 = subject_code2;
    }

    public String getSubject_code3() {
        return subject_code3;
    }

    public void setSubject_code3(String subject_code3) {
        this.subject_code3 = subject_code3;
    }

    public String getSubject_code4() {
        return subject_code4;
    }

    public void setSubject_code4(String subject_code4) {
        this.subject_code4 = subject_code4;
    }

    public String getSubject_code5() {
        return subject_code5;
    }

    public void setSubject_code5(String subject_code5) {
        this.subject_code5 = subject_code5;
    }

    public String getSubject_code6() {
        return subject_code6;
    }

    public void setSubject_code6(String subject_code6) {
        this.subject_code6 = subject_code6;
    }

    public String getSubject_code7() {
        return subject_code7;
    }

    public void setSubject_code7(String subject_code7) {
        this.subject_code7 = subject_code7;
    }

    public String getSubject_code8() {
        return subject_code8;
    }

    public void setSubject_code8(String subject_code8) {
        this.subject_code8 = subject_code8;
    }

    public String getSubject_code9() {
        return subject_code9;
    }

    public void setSubject_code9(String subject_code9) {
        this.subject_code9 = subject_code9;
    }

    public String getSubject_code10() {
        return subject_code10;
    }

    public void setSubject_code10(String subject_code10) {
        this.subject_code10 = subject_code10;
    }

    public String getSubject_code11() {
        return subject_code11;
    }

    public void setSubject_code11(String subject_code11) {
        this.subject_code11 = subject_code11;
    }

    public String getSubject_code12() {
        return subject_code12;
    }

    public void setSubject_code12(String subject_code12) {
        this.subject_code12 = subject_code12;
    }
}
