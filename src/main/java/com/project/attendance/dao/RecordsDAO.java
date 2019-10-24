package com.project.attendance.dao;

import com.project.attendance.model.Records;
import com.project.attendance.repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsDAO {
    @Autowired
    RecordsRepository recordsRepository;
    //to save a subject
    public Records save(Records records){
        return recordsRepository.save(records);
    }

    //to search all subjects
    public List<Records> findAll(){
        return recordsRepository.findAll();
    }

    //get a subject by id
    public Records findById(Long id){
        return recordsRepository.findById(id).orElse(null);
    }

    //get a subject by id
    public List<Records> getTimestamp (String day, int start, int end){
        return recordsRepository.getRecordsByDayAndTimeGreaterThanEqualAndTimeLessThanEqual(day,start,end);
    }

    //delete a subject
    public void delete(Long id){
        recordsRepository.deleteById(id);
    }


}

