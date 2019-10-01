package com.project.attendance.dao;

import com.project.attendance.model.Time;
import com.project.attendance.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TimeDAO {

    @Autowired
    TimeRepository timeRepository;

    //to save a subject
    public Time save(Time time){
        return timeRepository.save(time);
    }

    //to search all subjects
    public List<Time> findAll(){
        return timeRepository.findAll();
    }

    //get a subject by id
    public Time findById(Long id){
        return timeRepository.findById(id).orElse(null);
    }


    //delete a subject
    public void delete(Long id){
        timeRepository.deleteById(id);
    }


}
