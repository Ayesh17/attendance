package com.project.attendance.repository;

import com.project.attendance.model.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    //@Query("select enroll from Enroll enroll where enroll.userId = :userId AND enroll.year= :year AND enroll.semester= :semester")
    List<Enroll> getEnrollByIndexNumberAndYearAndSemester(@Param("indexNumber") int indexNumber,@Param("year") int year,@Param("semester") int semester);
}
