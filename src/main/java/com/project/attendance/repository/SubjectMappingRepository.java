package com.project.attendance.repository;

import com.project.attendance.model.Enroll;
import com.project.attendance.model.SubjectMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectMappingRepository extends JpaRepository<SubjectMapping, Long> {

    List<SubjectMapping> getSubjectMappingBySubjectCode(@Param("subjectCode") String subjectCode);
}
