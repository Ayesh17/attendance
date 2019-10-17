package com.project.attendance.repository;

import com.project.attendance.model.RecordsByStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsByStudentRepository extends JpaRepository<RecordsByStudent, Long> {
}
