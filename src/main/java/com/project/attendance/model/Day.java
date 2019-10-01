package com.project.attendance.model;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
//import com.project.attendance.model.Course;


@Entity
@Table(name="days")
@EntityListeners(AuditingEntityListener.class)

public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String name;


    public Day() {
    }

    public Day(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
