package com.project.attendance.dao;

import com.project.attendance.model.Subject;
import com.project.attendance.model.SubjectMapping;
import com.project.attendance.repository.SubjectMappingRepository;
import com.project.attendance.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    //to save all
    public void saveAll(List<SubjectMapping> subjectMapping){
        subjectMappingRepository.saveAll(subjectMapping);
    }

    //to search all subjects
    public List<SubjectMapping> findAll(){
        return subjectMappingRepository.findAll(Sort.by(Sort.Direction.ASC, "subjectCode"));
    }

    //get a subject by id
    public SubjectMapping findById(Long id){
        return subjectMappingRepository.findById(id).orElse(null);
    }

    //to search all subjects
    public List<SubjectMapping> getSubjectDetails(String subjectCode){
        return subjectMappingRepository.getSubjectMappingBySubjectCode(subjectCode);
    }

    //delete a subject
    public void delete(Long id){
        subjectMappingRepository.deleteById(id);
    }

}
