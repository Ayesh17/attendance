package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name="timetables")
@EntityListeners(AuditingEntityListener.class)
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String day;
    private String time;
    private String subject_code;
    private String group_code;

    public TimeTable () {
    }

    public TimeTable(String day, String time, String subject_code, String group_code) {
        this.day = day;
        this.time = time;
        this.subject_code = subject_code;
        this.group_code = group_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
