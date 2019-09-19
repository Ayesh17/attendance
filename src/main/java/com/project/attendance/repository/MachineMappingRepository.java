package com.project.attendance.repository;

import com.project.attendance.model.MachineMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineMappingRepository extends JpaRepository<MachineMapping, Long> {
}
