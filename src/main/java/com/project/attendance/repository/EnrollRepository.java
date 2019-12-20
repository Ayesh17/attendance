package com.project.attendance.repository;

import com.project.attendance.model.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    List<Enroll> getEnrollByIndexNumberAndYear(@Param("indexNumber") int indexNumber,@Param("year") String year);

    List<Enroll> getEnrollByIndexNumber(@Param("indexNumber") int indexNumber);

    List<Enroll> getDistinctByIndexNumber(List<Enroll> enroll);


    @Query("SELECT DISTINCT t.indexNumber  FROM Enroll t")
    List<Integer> findEnrollDistinctByIndexNumber();

}
