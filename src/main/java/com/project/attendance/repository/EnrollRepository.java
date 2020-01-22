package com.project.attendance.repository;

import com.project.attendance.model.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    List<Enroll> getEnrollByIndexNumberAndYear(@Param("indexNumber") int indexNumber,@Param("year") String year);

    List<Enroll> getEnrollByIndexNumberAndYearAndSemester(@Param("indexNumber") int indexNumber,@Param("year") String year,@Param("semester") int semester);

    List<Enroll> getEnrollByIndexNumber(@Param("indexNumber") int indexNumber);

    List<Enroll> getDistinctByIndexNumber(List<Enroll> enroll);


    @Query("SELECT DISTINCT t.indexNumber  FROM Enroll t")
    List<Integer> findEnrollDistinctByIndexNumber();

    @Query("SELECT t.year  FROM Enroll t where  t.indexNumber = :indexNumber")
    List<Enroll> findYearByIndexNumber(@Param("indexNumber") int indexNumber);


}
