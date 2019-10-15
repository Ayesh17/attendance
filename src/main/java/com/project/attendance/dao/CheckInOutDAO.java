package com.project.attendance.dao;

import com.project.attendance.model.CheckInOut;
import com.project.attendance.model.Records;
import com.project.attendance.repository.CheckInOutRepository;
import com.project.attendance.repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInOutDAO {
    @Autowired
    CheckInOutRepository checkInOutRepository;

    @Autowired
    RecordsRepository recordsRepository;
    //to save a subject
    public Records save(Records records){
        return recordsRepository.save(records);
    }

    //to search all subjects
    public List<CheckInOut> findAll(){
        return checkInOutRepository.findAll();
    }

    //get a subject by id
    public CheckInOut findById(Long id){
        return checkInOutRepository.findById(id).orElse(null);
    }


    //delete a subject
    public void delete(Long id){
        checkInOutRepository.deleteById(id);
    }


}
