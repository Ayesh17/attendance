package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name="subjectmappings")
@EntityListeners(AuditingEntityListener.class)

public class SubjectMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subjectCode;
    private String courseCode;
    private String hallCode;
    private String groupCode;
    private String day;
    private int start;
    private int end;
    private int year;
    private int semester;

    public SubjectMapping() {
    }

    public SubjectMapping(String subjectCode, String courseCode, String hallCode, String groupCode, String day, int start, int end, int year, int semester) {
        this.subjectCode = subjectCode;
        this.courseCode = courseCode;
        this.hallCode = hallCode;
        this.groupCode = groupCode;
        this.day = day;
        this.start = start;
        this.end = end;
        this.year = year;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getHallCode() {
        return hallCode;
    }

    public void setHallCode(String hallCode) {
        this.hallCode = hallCode;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
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

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
