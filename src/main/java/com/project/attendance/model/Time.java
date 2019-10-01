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
    private int start;
    private int end;

    public Time() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
