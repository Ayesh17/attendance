package com.project.attendance.repository;

import com.project.attendance.model.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamRepository extends JpaRepository<Stream,Long> {
}
