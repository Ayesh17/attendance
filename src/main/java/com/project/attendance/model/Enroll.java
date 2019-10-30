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
    private String subjectCode1;
    private String subjectCode2;
    private String subjectCode3;
    private String subjectCode4;
    private String subjectCode5;
    private String subjectCode6;
    private String subjectCode7;
    private String subjectCode8;
    private String subjectCode9;
    private String subjectCode10;
    private String subjectCode11;
    private String subjectCode12;
    private int year;
    private int semester;

    public Enroll() {
    }

    public Enroll(int userId, String name, String subjectCode1, String subjectCode2, String subjectCode3, String subjectCode4, String subjectCode5, String subjectCode6, String subjectCode7, String subjectCode8, String subjectCode9, String subjectCode10, String subjectCode11, String subjectCode12, int year, int semester) {
        this.userId = userId;
        this.name = name;
        this.subjectCode1 = subjectCode1;
        this.subjectCode2 = subjectCode2;
        this.subjectCode3 = subjectCode3;
        this.subjectCode4 = subjectCode4;
        this.subjectCode5 = subjectCode5;
        this.subjectCode6 = subjectCode6;
        this.subjectCode7 = subjectCode7;
        this.subjectCode8 = subjectCode8;
        this.subjectCode9 = subjectCode9;
        this.subjectCode10 = subjectCode10;
        this.subjectCode11 = subjectCode11;
        this.subjectCode12 = subjectCode12;
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

    public String getSubjectCode1() {
        return subjectCode1;
    }

    public void setSubjectCode1(String subjectCode1) {
        this.subjectCode1 = subjectCode1;
    }

    public String getSubjectCode2() {
        return subjectCode2;
    }

    public void setSubjectCode2(String subjectCode2) {
        this.subjectCode2 = subjectCode2;
    }

    public String getSubjectCode3() {
        return subjectCode3;
    }

    public void setSubjectCode3(String subjectCode3) {
        this.subjectCode3 = subjectCode3;
    }

    public String getSubjectCode4() {
        return subjectCode4;
    }

    public void setSubjectCode4(String subjectCode4) {
        this.subjectCode4 = subjectCode4;
    }

    public String getSubjectCode5() {
        return subjectCode5;
    }

    public void setSubjectCode5(String subjectCode5) {
        this.subjectCode5 = subjectCode5;
    }

    public String getSubjectCode6() {
        return subjectCode6;
    }

    public void setSubjectCode6(String subjectCode6) {
        this.subjectCode6 = subjectCode6;
    }

    public String getSubjectCode7() {
        return subjectCode7;
    }

    public void setSubjectCode7(String subjectCode7) {
        this.subjectCode7 = subjectCode7;
    }

    public String getSubjectCode8() {
        return subjectCode8;
    }

    public void setSubjectCode8(String subjectCode8) {
        this.subjectCode8 = subjectCode8;
    }

    public String getSubjectCode9() {
        return subjectCode9;
    }

    public void setSubjectCode9(String subjectCode9) {
        this.subjectCode9 = subjectCode9;
    }

    public String getSubjectCode10() {
        return subjectCode10;
    }

    public void setSubjectCode10(String subjectCode10) {
        this.subjectCode10 = subjectCode10;
    }

    public String getSubjectCode11() {
        return subjectCode11;
    }

    public void setSubjectCode11(String subjectCode11) {
        this.subjectCode11 = subjectCode11;
    }

    public String getSubjectCode12() {
        return subjectCode12;
    }

    public void setSubjectCode12(String subjectCode12) {
        this.subjectCode12 = subjectCode12;
    }
}
