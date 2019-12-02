package com.project.attendance.dao;

import com.project.attendance.model.TimeTable;
import com.project.attendance.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableDAO {

    @Autowired
    TimeTableRepository timeTableRepository;

    //to save a time table
    public TimeTable save(TimeTable timeTable){
        return timeTableRepository.save(timeTable);
    }

    //to save all the records of time table
    public TimeTable saveAll(TimeTable timeTable){
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
  public String getTimeTableNameById(Long id){return timeTableRepository.getTimeTableById(id).getName();}
}
