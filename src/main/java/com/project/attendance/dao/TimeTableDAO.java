package com.project.attendance.dao;

import com.project.attendance.model.TimeTable;
import com.project.attendance.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableDAO {

    @Autowired
    TimeTableRepository timeTableRepository;

    //to save a time table
    public TimeTable save(TimeTable timeTable){
        return timeTableRepository.save(timeTable);
    }

    //to search all machines
    public List<TimeTable> findAll(){
        return timeTableRepository.findAll();
    }

    //get a machine by id
    public TimeTable findById(Long id){
        return timeTableRepository.findById(id).orElse(null);
    }


    //delete a machine
    public void delete(Long id){
        timeTableRepository.deleteById(id);
    }
}
