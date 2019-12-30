package com.project.attendance.dao;

import com.project.attendance.model.StudentGroup;
import com.project.attendance.repository.StudentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    //to save all
    public void saveAll(List<StudentGroup> studentGroup){
        studentGroupRepository.saveAll(studentGroup);
    }

    //to search all subjects
    public List<StudentGroup> findAll(){
        return studentGroupRepository.findAll(Sort.by(Sort.Direction.ASC, "groupCode"));
    }

    //get a subject by id
    public StudentGroup findById(Long id){
        return studentGroupRepository.findById(id).orElse(null);
    }

    //delete a subject
    public void delete(Long id){
        studentGroupRepository.deleteById(id);
    }


    public StudentGroup findByGroupCode(String groupCode) {
        StudentGroup st= studentGroupRepository.getStudentGroupByGroupCode(groupCode);
        System.out.println("studentGroup");
        return  studentGroupRepository.getStudentGroupByGroupCode(groupCode);
    }
}
