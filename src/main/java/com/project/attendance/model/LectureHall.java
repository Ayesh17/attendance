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
    private String hall_code;
    private String name;

    public LectureHall() {
    }

    public LectureHall(String hall_code, String name) {
        this.hall_code = hall_code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHall_code() {
        return hall_code;
    }

    public void setHall_code(String hall_code) {
        this.hall_code = hall_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
