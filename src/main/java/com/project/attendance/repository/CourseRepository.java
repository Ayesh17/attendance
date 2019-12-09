package com.project.attendance.repository;

import com.project.attendance.model.Course;
import com.project.attendance.model.CourseMapping;
import com.project.attendance.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
