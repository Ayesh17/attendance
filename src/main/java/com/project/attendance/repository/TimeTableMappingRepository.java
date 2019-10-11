package com.project.attendance.repository;

        import com.project.attendance.model.TimeTableMapping;
                import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface TimeTableMappingRepository extends JpaRepository<TimeTableMapping, Long> {
        //Optional<TimeTableMapping> findById(String id);
        //TimeTableMapping findTimeTableMappingsByTime_table_code(String time_table_code);
        @Query("SELECT DISTINCT t.code  FROM TimeTableMapping t")
        List<Long> findTimeTableMappingDistinctByCode();

        //public List<TimeTableMapping> findDistinctByCode();
}

