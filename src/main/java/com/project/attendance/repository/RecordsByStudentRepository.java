package com.project.attendance.repository;

import com.project.attendance.model.RecordsByStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordsByStudentRepository extends JpaRepository<RecordsByStudent, Long> {
    List<RecordsByStudent> getRecordsByIndexNumberAndYearAndSemester(@Param("indexNumber") int indexNumber,@Param("year") String year,@Param("semester") int semester);

}
