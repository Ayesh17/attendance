package com.project.attendance.repository;

import com.project.attendance.model.Enroll;
import com.project.attendance.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    Lecturer getLecturerByFingerId(@Param("indexNumber") int fingerId);

}
