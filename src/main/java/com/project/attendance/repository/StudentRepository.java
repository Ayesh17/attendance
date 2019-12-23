package com.project.attendance.repository;

import com.project.attendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByIndexNumber(int indexNumber);

    List<Student> findByStreamCodeAndRegistrationNumberStartingWith(@Param("streamCode") String streamCode, @Param("year") String year);

}
