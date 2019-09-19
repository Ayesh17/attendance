package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="machines")
@EntityListeners(AuditingEntityListener.class)
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String machine_code;
    private String machine_index;

    public Machine() {
    }

    public Machine(String machine_code, String machine_index) {
        this.machine_code = machine_code;
        this.machine_index = machine_index;
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

    public String getMachine_index() {
        return machine_index;
    }

    public void setMachine_index(String machine_index) {
        this.machine_index = machine_index;
    }
}
