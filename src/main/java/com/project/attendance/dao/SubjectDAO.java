package com.project.attendance.dao;

import com.project.attendance.model.Subject;
import com.project.attendance.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectDAO {

    @Autowired
    SubjectRepository subjectRepository;

    //to save a subject
    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }

    //to search all subjects
    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    //get a subject by id
    public Subject findById(Long id){
        return subjectRepository.findById(id).orElse(null);
    }


    //delete a subject
    public void delete(Long id){
        subjectRepository.deleteById(id);
    }


}
