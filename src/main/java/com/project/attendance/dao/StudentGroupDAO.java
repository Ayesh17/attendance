package com.project.attendance.dao;

import com.project.attendance.model.StudentGroup;
import com.project.attendance.repository.StudentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupDAO {

    @Autowired
    StudentGroupRepository studentGroupRepository;

    //to save a subject
    public StudentGroup save(StudentGroup studentGroup){
        return studentGroupRepository.save(studentGroup);
    }

    //to search all subjects
    public List<StudentGroup> findAll(){
        return studentGroupRepository.findAll();
    }

    //get a subject by id
    public StudentGroup findById(Long id){
        return studentGroupRepository.findById(id).orElse(null);
    }


    //delete a subject
    public void delete(Long id){
        studentGroupRepository.deleteById(id);
    }


}
