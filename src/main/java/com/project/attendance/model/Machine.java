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
    private String machineCode;
    private String machineIndex;

    public Machine() {
    }

    public Machine(String machineCode, String machineIndex) {
        this.machineCode = machineCode;
        this.machineIndex = machineIndex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMachineIndex() {
        return machineIndex;
    }

    public void setMachineIndex(String machineIndex) {
        this.machineIndex = machineIndex;
    }
}
