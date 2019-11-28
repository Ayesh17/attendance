package com.project.attendance.dao;

import com.project.attendance.model.RecordsByStudent;
import com.project.attendance.repository.RecordsByStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsByStudentDAO {

    @Autowired
    RecordsByStudentRepository recordsByStudentRepository;

    //to save all
    public void saveAll(List<RecordsByStudent> recordsByStudents){
        recordsByStudentRepository.saveAll(recordsByStudents);
    }
    //to save
    public RecordsByStudent save(RecordsByStudent recordsByStudent){
        return recordsByStudentRepository.save(recordsByStudent);
    }

    //to search all
    public List<RecordsByStudent> findAll(){
        return recordsByStudentRepository.findAll();
    }

    //get by id
    public RecordsByStudent findById(Long id){
        return recordsByStudentRepository.findById(id).orElse(null);
    }

    //get by userId
    public List<RecordsByStudent> getByIndexNumber(int indexNumber,int year,int semester){return  recordsByStudentRepository.getRecordsByIndexNumberAndYearAndSemester(indexNumber,year,semester);}

    //delete
    public void delete(Long id){
        recordsByStudentRepository.deleteById(id);
    }


}
