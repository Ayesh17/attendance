package com.project.attendance.repository;

import com.project.attendance.model.Student;
import com.project.attendance.model.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    StudentGroup getStudentGroupByGroupCode(@Param("groupCode") String groupCode);


}
