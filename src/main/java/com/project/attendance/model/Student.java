package com.project.attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="students")
@EntityListeners(AuditingEntityListener.class)

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private int indexNumber;
    @NotBlank
    private String registrationNumber;
    @NotNull
    private int user_id;
    private String name;
    private String course;
    private String address;
    private String nic;

    public Student() {
    }

    public Student(@NotBlank int indexNumber, @NotBlank String registrationNumber, @NotBlank int user_id, String name, String course, String address, String nic) {
        this.indexNumber = indexNumber;
        this.registrationNumber = registrationNumber;
        this.user_id = user_id;
        this.name = name;
        this.course = course;
        this.address = address;
        this.nic = nic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
