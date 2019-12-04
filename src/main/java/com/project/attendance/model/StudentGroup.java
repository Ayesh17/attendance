package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="studentgroups")
@EntityListeners(AuditingEntityListener.class)
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupCode;
    private String streamCode;
    private String year;

    public StudentGroup () {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getStreamCode() {
        return streamCode;
    }

    public void setStreamCode(String streamCode) {
        this.streamCode = streamCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
