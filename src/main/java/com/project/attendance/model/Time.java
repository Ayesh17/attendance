package com.project.attendance.model;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
//import com.project.attendance.model.Course;


@Entity
@Table(name="times")
@EntityListeners(AuditingEntityListener.class)

public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String start;
    private String end;

    public Time() {
    }

    public Time(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
