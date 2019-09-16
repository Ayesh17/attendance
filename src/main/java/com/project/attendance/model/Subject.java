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
    private String hall_code;
    private String course_code;

    public Subject() {
    }

    public Subject(String subject_code, String name, String hall_code, String course_code) {
        this.subject_code = subject_code;
        this.name = name;
        this.hall_code = hall_code;
        this.course_code = course_code;
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

    public String getHall_code() {
        return hall_code;
    }

    public void setHall_code(String hall_code) {
        this.hall_code = hall_code;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }
}
