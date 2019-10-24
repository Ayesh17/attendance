package com.project.attendance.repository;

import com.project.attendance.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordsRepository extends JpaRepository<Records, Long> {

    List<Records> getRecordsByDayAndTimeGreaterThanEqualAndTimeLessThanEqual(@Param("day") String day,@Param("start") int start,@Param("end") int end);


}
