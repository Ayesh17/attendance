package com.project.attendance.repository;

import com.project.attendance.model.TimeTable;
import com.project.attendance.model.TimeTableMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    TimeTable getTimeTableById(@Param("id") Long id);
}