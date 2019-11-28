package com.project.attendance.repository;

import com.project.attendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByIndexNumber(int indexNumber);
}
