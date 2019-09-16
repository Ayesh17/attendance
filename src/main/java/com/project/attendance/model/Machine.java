package com.project.attendance.model;

public class Machine {
    private Long id;
    private String code;
    private String hall_code;

    public Machine() {
    }

    public Machine(Long id, String code, String hall_code) {
        this.id = id;
        this.code = code;
        this.hall_code = hall_code;
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

    public String getHall_code() {
        return hall_code;
    }

    public void setHall_code(String hall_code) {
        this.hall_code = hall_code;
    }

}
