package com.project.attendance.repository;

import com.project.attendance.model.CourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseMappingRepository extends JpaRepository<CourseMapping, Long> {

    List<CourseMapping> getCourseMappingByCourseCode(@Param("courseCode") String courseCode);

    List<CourseMapping> getCourseMappingByLecturer1CodeOrLecturer2Code(@Param("lecturer1Code") String lecturer1Code,@Param("lecturer2Code") String lecturer2Code);

}
