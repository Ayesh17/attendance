package com.project.attendance.repository;

import com.project.attendance.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {
}
