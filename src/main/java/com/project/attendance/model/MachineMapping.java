package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="machinemapping")
@EntityListeners(AuditingEntityListener.class)
public class MachineMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String machine_code;
    private String hall_code;

    public MachineMapping() {
    }

    public MachineMapping(String machine_code, String hall_code) {
        this.machine_code = machine_code;
        this.hall_code = hall_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachine_code() {
        return machine_code;
    }

    public void setMachine_code(String machine_code) {
        this.machine_code = machine_code;
    }

    public String getHall_code() {
        return hall_code;
    }

    public void setHall_code(String hall_code) {
        this.hall_code = hall_code;
    }
}
