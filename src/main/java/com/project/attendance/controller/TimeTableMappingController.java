package com.project.attendance.controller;

import com.project.attendance.dao.*;
import com.project.attendance.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TimeTableMappingController {
    @Autowired
    private  TimeTableMappingDAO timeTableMappingDAO;

    @Autowired
    private TimeTableDAO timeTableDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private DayDAO dayDAO;

    @Autowired
    private TimeDAO timeDAO;



    @RequestMapping("/timeTableMapping")
    public String viewHomePage(Model model){
        System.out.println("hey");
      //  timeTableMappingDAO.updateSubjectCode();
        List<Long> timeTableMappingDetails= timeTableMappingDAO.findDistinct();
       // List<TimeTableMapping>  timeTableMappingDetails=timeTableMappingDAO.select(timeTableMappingDetails1);
        System.out.println("hey");
        System.out.println(timeTableMappingDetails.toString());
        List<TimeTableMapping> tempList=new ArrayList<>();

        for(int i = 0 ; i < timeTableMappingDetails.size(); i++) {
            TimeTableMapping tempTimeTable = new TimeTableMapping();
            tempTimeTable.setCode(timeTableMappingDetails.get(i));
            tempList.add(tempTimeTable);
            System.out.println(timeTableMappingDetails.get(i));

        }
        model.addAttribute("timeTableMappingDetails",tempList);

        return "timeTableMapping";
    }


    @RequestMapping(value="/timeTableMapping/saveAll",method= RequestMethod.POST)
    public String saveTimeTable(@ModelAttribute("timeTableMapping") TimeTableMapping timeTableMapping){

        String[] dayArray = timeTableMapping.getDay().split(",");
        String[] subArray = timeTableMapping.getCourseCode().split(",");
        String[] startArray = timeTableMapping.getStart().split(",");
        String[] endArray = timeTableMapping.getEnd().split(",");

        String[] uniqueDay = Arrays.stream(dayArray).distinct().toArray(String[]::new);
        String[] uniqueStart = Arrays.stream(startArray).distinct().toArray(String[]::new);
        String[] uniqueEnd = Arrays.stream(endArray).distinct().toArray(String[]::new);


        List<TimeTableMapping> tempList = new ArrayList<>();
        int count=0;
        for(int j=0;j<uniqueStart.length;j++) {

            for(int i = 0 ; i < uniqueDay.length; i++) {
                TimeTableMapping tempTimeTable = new TimeTableMapping();
                tempTimeTable.setStart(uniqueStart[j]);
                System.out.println(uniqueStart[j]);

                tempTimeTable.setEnd(uniqueEnd[j]);
                tempTimeTable.setTime_table_code(timeTableMapping.getTime_table_code());
                tempTimeTable.setCode(timeTableMapping.getCode());
                tempTimeTable.setDay(uniqueDay[i]);
                tempTimeTable.setCourseCode(subArray[count]);
                tempList.add(tempTimeTable);
                count++;

            }
        }

        timeTableMappingDAO.saveAll(tempList);
        return  "redirect:/timeTableMapping";
    }

    @RequestMapping("/timeTableMapping/new")
    public String addTimeTableMapping(Model model){
        System.out.println("hey");
        System.out.println("hey");
        TimeTableMapping timeTableMapping =new TimeTableMapping();
        model.addAttribute("timeTableMapping",timeTableMapping);

        List<TimeTable> timeTableDetail = timeTableDAO.findAll();
        model.addAttribute("timeTables", timeTableDetail);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        String[] days = new String[] { "Monday", "Tuesday"};

        List<Day> dayDetails = dayDAO.findAll();
        model.addAttribute("days",dayDetails);

        List<Time> timeDetails = timeDAO.findAll();
        model.addAttribute("times",timeDetails);

        return "addTimeTableMapping";
    }

    @RequestMapping(value="/timeTableMapping/saveUpdated",method= RequestMethod.POST)
    public String saveUpdatedTimeTable(@ModelAttribute("timeTableMapping") TimeTableMapping timeTableMapping){

        Long code   = timeTableMapping.getCode();

        List<TimeTableMapping> map=timeTableMappingDAO.getTimeTableMappingsByCode(code);

        Long[] idArray=new Long[map.size()];
        for(int i=0;i<map.size();i++){
            idArray[i]=map.get(i).getId();
        }

        for(int i=0;i<idArray.length;i++) {
            System.out.println(idArray[i]);
        }

        String[] subArray = timeTableMapping.getCourseCode().split(",");

        List<TimeTableMapping> tempList = new ArrayList<>();

        for(int i=0;i<subArray.length;i++){
            TimeTableMapping tempTimeTable = new TimeTableMapping();
            if(subArray[i].isEmpty()){
                System.out.println(i+ "is null");
            }else {
                tempTimeTable.setCourseCode(subArray[i]);
                tempTimeTable.setId(idArray[i]);
                tempList.add(tempTimeTable);
            }
        }

        for(int i=0;i<tempList.size();i++){
            timeTableMappingDAO.updateCourseCode(tempList.get(i).getId(),tempList.get(i).getCourseCode());
        }

        return  "redirect:/timeTableMapping";
    }

    @RequestMapping("/timeTableMapping/edit/{id}")
    public ModelAndView updateTimeTable(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateTimeTableMapping"));

        mav.addObject("code",id);


        List<TimeTable> timeTableDetails = timeTableDAO.findAll();
        mav.addObject("timeTables", timeTableDetails);

        String  timeTableName = timeTableDAO.getTimeTableNameById(id);
        mav.addObject("timeTableName", timeTableName);

        List<Course> courseDetails = courseDAO.findAll();
        mav.addObject("courses", courseDetails);

        List<StudentGroup> studentGroupDetails = studentGroupDAO.findAll();
        mav.addObject("studentGroups",studentGroupDetails);

        List<Day> dayDetails = dayDAO.findAll();
        mav.addObject("days",dayDetails);

        List<Time> timeDetails = timeDAO.findAll();
        mav.addObject("times",timeDetails);

        List<TimeTableMapping> timeTableMappingList=timeTableMappingDAO.getTimeTableMappingsByCode(id);
        mav.addObject("timeTableMapping", timeTableMappingList.get(3));

        List<TimeTableMapping> timeTableMappingList2=timeTableMappingDAO.getTimeTableMappingsByCode(id);
        mav.addObject("timeTableMapping2", timeTableMappingList2);


        String[][] courses=new String[2][5];
        int count=0;
        for(int i=0;i<2;i++){
            for(int j=0;j<5;j++){
                courses[i][j]= timeTableMappingList.get(count).getCourseCode();
                System.out.println("Course: "+courses[i][j]);
                count++;
            }
        }


        return  mav;
    }

    @RequestMapping("/timeTableMapping/delete/{code}")
    public String deleteProduct(@PathVariable(name="code") Long code){
        timeTableDAO.delete(code);
        return  "redirect:/timeTableMapping";
    }
}

