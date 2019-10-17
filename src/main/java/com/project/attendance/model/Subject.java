package com.project.attendance.model;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
//import com.project.attendance.model.Course;


@Entity
@Table(name="subjects")
@EntityListeners(AuditingEntityListener.class)

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject_code;
    private String name;

    public Subject() {
    }

    public Subject(String subject_code, String name) {
        this.subject_code = subject_code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
