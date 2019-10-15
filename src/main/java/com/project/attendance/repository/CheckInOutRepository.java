package com.project.attendance.repository;

import com.project.attendance.model.CheckInOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInOutRepository extends JpaRepository<CheckInOut, Long> {



}
