package com.project.attendance.repository;

import com.project.attendance.model.SubjectMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectMappingRepository extends JpaRepository<SubjectMapping, Long> {
}
