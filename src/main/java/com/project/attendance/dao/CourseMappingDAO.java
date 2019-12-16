package com.project.attendance.dao;

import com.project.attendance.model.CourseMapping;
import com.project.attendance.repository.CourseMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMappingDAO {

    @Autowired
    CourseMappingRepository courseMappingRepository;

    //to save a subject
    public CourseMapping save(CourseMapping courseMapping){
        return courseMappingRepository.save(courseMapping);
    }

    //to save all
    public void saveAll(List<CourseMapping> courseMapping){
        courseMappingRepository.saveAll(courseMapping);
    }

    //to search all subjects
    public List<CourseMapping> findAll(){
        return courseMappingRepository.findAll(Sort.by(Sort.Direction.ASC, "courseCode"));
    }

    //get a subject by id
    public CourseMapping findById(Long id){
        return courseMappingRepository.findById(id).orElse(null);
    }

    //to search all subjects
    public List<CourseMapping> getCourseDetails(String courseCode){
        return courseMappingRepository.getCourseMappingByCourseCode(courseCode);
    }

    //to search all subjects assigned as lecturer
    public List<CourseMapping> getCoursesByLecturer(String lecturerCode){
        return courseMappingRepository.getCourseMappingByLecturer1CodeOrLecturer2Code(lecturerCode,lecturerCode);
    }


    //delete a subject
    public void delete(Long id){
        courseMappingRepository.deleteById(id);
    }

}
