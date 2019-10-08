package com.project.attendance.repository;

        import com.project.attendance.model.TimeTableMapping;
                import org.springframework.data.jpa.repository.JpaRepository;
                import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableMappingRepository extends JpaRepository<TimeTableMapping, Long> {
}
