package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="checkinout")
@EntityListeners(AuditingEntityListener.class)

public class CheckInOut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int userid;
    private String day;
    private int time;
    private int sensorid;
    private String checktime;


    public CheckInOut() {
    }

    public CheckInOut(int userid, String day, int time, int sensorid, String checktime) {
        this.userid = userid;
        this.day = day;
        this.time = time;
        this.sensorid = sensorid;
        this.checktime = checktime;
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

    public int getSensorid() {
        return sensorid;
    }

    public void setSensorid(int sensorid) {
        this.sensorid = sensorid;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }
}
