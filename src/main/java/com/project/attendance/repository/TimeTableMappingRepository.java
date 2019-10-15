package com.project.attendance.repository;

        import com.project.attendance.model.TimeTableMapping;
                import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface TimeTableMappingRepository extends JpaRepository<TimeTableMapping, Long> {

        @Query("SELECT DISTINCT t.code  FROM TimeTableMapping t")
        List<Long> findTimeTableMappingDistinctByCode();

        List<TimeTableMapping> findByCode(Long code);
        List<TimeTableMapping> findAllByDay(String day);

        @Modifying
        @Query("UPDATE TimeTableMapping t SET t.subject_code = :subject_code Where t.id = :id")
        int updateSubjectCode(@Param("id") Long id, @Param("subject_code") String subject_code);


}

