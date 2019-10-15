package com.project.attendance.dao;

import com.project.attendance.model.CheckInOut;
import com.project.attendance.repository.CheckInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInOutDAO {
    @Autowired
    CheckInOutRepository checkInOutRepository;

    //to save a subject
    public CheckInOut save(CheckInOut dateToDay){
        return checkInOutRepository.save(dateToDay);
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
