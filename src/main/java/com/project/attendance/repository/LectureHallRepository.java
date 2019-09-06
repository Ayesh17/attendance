package com.project.attendance.repository;

import com.project.attendance.model.LectureHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureHallRepository extends JpaRepository<LectureHall, Long> {
}
