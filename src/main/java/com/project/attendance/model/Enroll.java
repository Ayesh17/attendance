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
    private String courseCode1;
    private String courseCode2;
    private String courseCode3;
    private String courseCode4;
    private String courseCode5;
    private String courseCode6;
    private String courseCode7;
    private String courseCode8;
    private String courseCode9;
    private String courseCode10;
    private String courseCode11;
    private String courseCode12;
    private int year;
    private int semester;

    public Enroll() {
    }

    public Enroll(int userId, String name, String courseCode1, String courseCode2, String courseCode3, String courseCode4, String courseCode5, String courseCode6, String courseCode7, String courseCode8, String courseCode9, String courseCode10, String courseCode11, String courseCode12, int year, int semester) {
        this.userId = userId;
        this.name = name;
        this.courseCode1 = courseCode1;
        this.courseCode2 = courseCode2;
        this.courseCode3 = courseCode3;
        this.courseCode4 = courseCode4;
        this.courseCode5 = courseCode5;
        this.courseCode6 = courseCode6;
        this.courseCode7 = courseCode7;
        this.courseCode8 = courseCode8;
        this.courseCode9 = courseCode9;
        this.courseCode10 = courseCode10;
        this.courseCode11 = courseCode11;
        this.courseCode12 = courseCode12;
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

    public String getCourseCode1() {
        return courseCode1;
    }

    public void setCourseCode1(String courseCode1) {
        this.courseCode1 = courseCode1;
    }

    public String getCourseCode2() {
        return courseCode2;
    }

    public void setCourseCode2(String courseCode2) {
        this.courseCode2 = courseCode2;
    }

    public String getCourseCode3() {
        return courseCode3;
    }

    public void setCourseCode3(String courseCode3) {
        this.courseCode3 = courseCode3;
    }

    public String getCourseCode4() {
        return courseCode4;
    }

    public void setCourseCode4(String courseCode4) {
        this.courseCode4 = courseCode4;
    }

    public String getCourseCode5() {
        return courseCode5;
    }

    public void setCourseCode5(String courseCode5) {
        this.courseCode5 = courseCode5;
    }

    public String getCourseCode6() {
        return courseCode6;
    }

    public void setCourseCode6(String courseCode6) {
        this.courseCode6 = courseCode6;
    }

    public String getCourseCode7() {
        return courseCode7;
    }

    public void setCourseCode7(String courseCode7) {
        this.courseCode7 = courseCode7;
    }

    public String getCourseCode8() {
        return courseCode8;
    }

    public void setCourseCode8(String courseCode8) {
        this.courseCode8 = courseCode8;
    }

    public String getCourseCode9() {
        return courseCode9;
    }

    public void setCourseCode9(String courseCode9) {
        this.courseCode9 = courseCode9;
    }

    public String getCourseCode10() {
        return courseCode10;
    }

    public void setCourseCode10(String courseCode10) {
        this.courseCode10 = courseCode10;
    }

    public String getCourseCode11() {
        return courseCode11;
    }

    public void setCourseCode11(String courseCode11) {
        this.courseCode11 = courseCode11;
    }

    public String getCourseCode12() {
        return courseCode12;
    }

    public void setCourseCode12(String courseCode12) {
        this.courseCode12 = courseCode12;
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
