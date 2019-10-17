package com.project.attendance.dao;

import com.project.attendance.model.Subject;
import com.project.attendance.model.SubjectMapping;
import com.project.attendance.repository.SubjectMappingRepository;
import com.project.attendance.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectMappingDAO {

    @Autowired
    SubjectMappingRepository subjectMappingRepository;

    //to save a subject
    public SubjectMapping save(SubjectMapping subjectMapping){
        return subjectMappingRepository.save(subjectMapping);
    }

    //to search all subjects
    public List<SubjectMapping> findAll(){
        return subjectMappingRepository.findAll();
    }

    //get a subject by id
    public SubjectMapping findById(Long id){
        return subjectMappingRepository.findById(id).orElse(null);
    }


    //delete a subject
    public void delete(Long id){
        subjectMappingRepository.deleteById(id);
    }

}
