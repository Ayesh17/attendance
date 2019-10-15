package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="records")
@EntityListeners(AuditingEntityListener.class)

public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int userid;
    private String day;
    private int time;
    private String timestamp;


    public Records() {
    }

    public Records(int userid, String day, int time) {
        this.userid = userid;
        this.day = day;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
