package com.project.attendance.repository;

        import com.project.attendance.model.Course;
        import com.project.attendance.model.TimeTableMapping;
                import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface TimeTableMappingRepository extends JpaRepository<TimeTableMapping, Long> {

        @Query("SELECT DISTINCT t.code  FROM TimeTableMapping t")
        List<Long> findTimeTableMappingDistinctByCode();

        List<TimeTableMapping> findByCode(Long code);
        List<TimeTableMapping> findAllByDay(String day);

        @Modifying
        @Transactional
        @Query("UPDATE TimeTableMapping t SET t.courseCode = :courseCode Where t.id = :id")
        int updateCourseCode(@Param("id") Long id, @Param("courseCode") String courseCode);



        List<TimeTableMapping> getTimeTableMappingsByCode(@Param("code") Long code);

        List<TimeTableMapping> getTimeTableMappingsByCourseCode(@Param("course_code") String courseCode);

       // List<TimeTableMapping> getTimeTableMappingByCourseCode(@Param("courseCode") String courseCode);







}

