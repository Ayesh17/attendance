package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name="coursemappings")
@EntityListeners(AuditingEntityListener.class)

public class CourseMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseCode;
    private String streamCode;
    private String hallCode;
    private String groupCode;
    private String day;
    private int start;
    private int end;
    private int year;
    private int semester;
    private String lecturer1Code;
    private String lecturer2Code;

    public CourseMapping() {
    }

    public CourseMapping(String courseCode, String streamCode, String hallCode, String groupCode, String day, int start, int end, int year, int semester, String lecturer1Code, String lecturer2Code) {
        this.courseCode = courseCode;
        this.streamCode = streamCode;
        this.hallCode = hallCode;
        this.groupCode = groupCode;
        this.day = day;
        this.start = start;
        this.end = end;
        this.year = year;
        this.semester = semester;
        this.lecturer1Code = lecturer1Code;
        this.lecturer2Code = lecturer2Code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStreamCode() {
        return streamCode;
    }

    public void setStreamCode(String streamCode) {
        this.streamCode = streamCode;
    }

    public String getHallCode() {
        return hallCode;
    }

    public void setHallCode(String hallCode) {
        this.hallCode = hallCode;
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

    public String getLecturer1Code() {
        return lecturer1Code;
    }

    public void setLecturer1Code(String lecturer1Code) {
        this.lecturer1Code = lecturer1Code;
    }

    public String getLecturer2Code() {
        return lecturer2Code;
    }

    public void setLecturer2Code(String lecturer2Code) {
        this.lecturer2Code = lecturer2Code;
    }
}
