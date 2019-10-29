package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name="recordsbystudent")
@EntityListeners(AuditingEntityListener.class)

public class RecordsByStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int userId;
    private int year;
    private int semester;
    private String subject;
    private String days;
    private int count;
    private String timeStamp;

    public RecordsByStudent() {
    }

    public RecordsByStudent(int userId, int year, int semester, String subject, String days, int count) {
        this.userId = userId;
        this.year = year;
        this.semester = semester;
        this.subject = subject;
        this.days = days;
        this.count = count;
    }

    public RecordsByStudent(int userId, int year, int semester, String subject, String days, int count, String timeStamp) {
        this.userId = userId;
        this.year = year;
        this.semester = semester;
        this.subject = subject;
        this.days = days;
        this.count = count;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}