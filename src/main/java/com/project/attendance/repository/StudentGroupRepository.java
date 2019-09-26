package com.project.attendance.repository;

import com.project.attendance.model.Student;
import com.project.attendance.model.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
}
