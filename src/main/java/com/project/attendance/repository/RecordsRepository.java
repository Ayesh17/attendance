package com.project.attendance.repository;

import com.project.attendance.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsRepository extends JpaRepository<Records, Long> {
}
