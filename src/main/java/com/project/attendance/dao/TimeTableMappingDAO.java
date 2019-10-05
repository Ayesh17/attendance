package com.project.attendance.dao;

import com.project.attendance.model.TimeTable;
import com.project.attendance.model.TimeTableMapping;
import com.project.attendance.repository.TimeTableMappingRepository;
import com.project.attendance.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableMappingDAO {

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    TimeTableMappingRepository timeTableMappingRepository;

    //to save a time table
    public TimeTableMapping save(TimeTableMapping timeTableMapping){
        return timeTableMappingRepository.save(timeTableMapping);
    }

    /*
    //@Override
    public void saveAll(List<TimeTableMapping> timeTableMapping) {

        List<TimeTableMapping> timetable=new ArrayList<>();
        timeTableMappingRepository.saveAll(timetable).forEach(timeTableMapping::add);   // TimeTable repository
    }
    */


    public void saveAll(List<TimeTableMapping> timeTableMapping){ timeTableMappingRepository.saveAll(timeTableMapping);}


        //to search all machines
    public List<TimeTableMapping> findAll(){
        return timeTableMappingRepository.findAll();
    }

    //get a machine by id
    public TimeTableMapping findById(Long id){
        return timeTableMappingRepository.findById(id).orElse(null);
    }


    //delete a machine
    public void delete(Long id){
        timeTableMappingRepository.deleteById(id);
    }

  /*  public void insertData(List<TimeTable> timeTableList){
        String sql="INSERT INTO TIMETABLES"+"(start,end,day,subject_code,group_code,year,semester) VALUES(?,?,?,?,?,?,?)";
        List<Object[]> batchArgsList=new ArrayList<Object[]>();

        for(TimeTable timeTable : timeTableList)
        {
            Object[] objectArray = { timeTable.getStart(),timeTable.getEnd(),timeTable.getDay(),timeTable.getSubject_code(),timeTable.getGroup_code(),timeTable.getYear(),timeTable.getSemester()};
            batchArgsList.add(objectArray);
        }

        getJdbcTemplate().batchUpdate(sql, batchArgsList);

    }*/
}
