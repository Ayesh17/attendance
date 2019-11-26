package com.project.attendance.dao;

import com.project.attendance.model.Course;
import com.project.attendance.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseDAO {

    @Autowired
    CourseRepository courseRepository;

    //to save a course
    public Course save(Course course){
        return courseRepository.save(course);
    }

    //to save all
    public void saveAll(List<Course> course){
        courseRepository.saveAll(course);
    }

    //to search all subjects
    public List<Course> findAll(){
        return courseRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //get a subject by id
    public Course findById(Long id){
        return courseRepository.findById(id).orElse(null);
    }


    //delete a subject
    public void delete(Long id){
        courseRepository.deleteById(id);
    }


}
