package com.project.attendance.repository;

import com.project.attendance.model.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {


}
