package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="lecturehalls")
@EntityListeners(AuditingEntityListener.class)

public class LectureHall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String hallCode;
    private String name;

    public LectureHall() {
    }

    public LectureHall(String hallCode, String name) {
        this.hallCode = hallCode;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHallCode() {
        return hallCode;
    }

    public void setHallCode(String hallCode) {
        this.hallCode = hallCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
