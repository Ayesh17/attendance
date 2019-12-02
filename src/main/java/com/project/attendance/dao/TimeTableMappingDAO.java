package com.project.attendance.dao;

import com.project.attendance.model.Course;
import com.project.attendance.model.TimeTableMapping;
import com.project.attendance.repository.TimeTableMappingRepository;
import com.project.attendance.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableMappingDAO {

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    TimeTableMappingRepository timeTableMappingRepository;


    //to save
    public TimeTableMapping save(TimeTableMapping timeTableMapping){
        return timeTableMappingRepository.save(timeTableMapping);
    }

    //to save all
    public void saveAll(List<TimeTableMapping> timeTableMapping){
        timeTableMappingRepository.saveAll(timeTableMapping);
    }

        //to search all
    public List<TimeTableMapping> findAll(){
        return timeTableMappingRepository.findAll();
    }

    public List<Long> findDistinct(){
      return timeTableMappingRepository.findTimeTableMappingDistinctByCode();
   }

    //get by id
    public TimeTableMapping findById(Long id){
        return timeTableMappingRepository.findById(id).orElse(null);
    }

    public TimeTableMapping findAllByDay(String day){
             return (TimeTableMapping) timeTableMappingRepository.findAllByDay(day);
        }

    public void updateCourseCode(Long id,String courseCode){
         timeTableMappingRepository.updateCourseCode(id,courseCode);
    }

    //delete
    public void delete(Long id){
        timeTableMappingRepository.deleteById(id);
    }

    public List<TimeTableMapping> getTimeTableMappingsByCode(Long code){ return timeTableMappingRepository.getTimeTableMappingsByCode(code);}


}
