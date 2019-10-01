package com.project.attendance.dao;

import com.project.attendance.model.Day;
import com.project.attendance.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DayDAO {

    @Autowired
    DayRepository dayRepository;

    //to save a subject
    public Day save(Day day){
        return dayRepository.save(day);
    }

    //to search all subjects
    public List<Day> findAll(){
        return dayRepository.findAll();
    }

    //get a subject by id
    public Day findById(Long id){
        return dayRepository.findById(id).orElse(null);
    }


    //delete a subject
    public void delete(Long id){
        dayRepository.deleteById(id);
    }


}
