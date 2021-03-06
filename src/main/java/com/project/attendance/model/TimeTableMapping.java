package com.project.attendance.model;

        import org.springframework.data.jpa.domain.support.AuditingEntityListener;

        import javax.persistence.*;
        import java.util.List;


@Entity
@Table(name="timetablemappings")
@EntityListeners(AuditingEntityListener.class)
public class TimeTableMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String day;
    private String start;
    private String end;
    private String courseCode;
    private String time_table_code;
    private Long code;

    public TimeTableMapping () {
    }

    public TimeTableMapping(Long code) {
        this.code = code;
    }

    public TimeTableMapping(String day, String start, String end, String courseCode, String time_table_code, Long code) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.courseCode = courseCode;
        this.time_table_code = time_table_code;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTime_table_code() {
        return time_table_code;
    }

    public void setTime_table_code(String time_table_code) {
        this.time_table_code = time_table_code;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
