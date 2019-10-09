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
    private SubjectDAO subjectDAO;

    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private DayDAO dayDAO;

    @Autowired
    private TimeDAO timeDAO;



    @RequestMapping("/timeTableMapping")
    public String viewHomePage(Model model){
        List<TimeTableMapping> timeTableMappingDetails= timeTableMappingDAO.findAll();
        model.addAttribute("timeTableMappingDetails",timeTableMappingDetails);
        return "timeTableMapping";
    }


    @RequestMapping(value="/timeTableMapping/saveAll",method= RequestMethod.POST)
    public String saveTimeTable(@ModelAttribute("timeTableMapping") TimeTableMapping timeTableMapping){

        String[] dayArray = timeTableMapping.getDay().split(",");
        String[] subArray = timeTableMapping.getSubject_code().split(",");
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
                tempTimeTable.setDay(uniqueDay[i]);
                tempTimeTable.setSubject_code(subArray[count]);
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

        List<Subject> subjectDetail = subjectDAO.findAll();
        model.addAttribute("subjects", subjectDetail);

        String[] days = new String[] { "Monday", "Tuesday"};

        List<Day> dayDetails = dayDAO.findAll();
        model.addAttribute("days",dayDetails);

        List<Time> timeDetails = timeDAO.findAll();
        model.addAttribute("times",timeDetails);

        return "addTimeTableMapping";
    }


    @RequestMapping("/timeTableMapping/edit/{id}")
    public ModelAndView updateTimeTable(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateTimeTableMapping"));

        //mav.addObject("timeTableMapping", new TimeTableMapping());

        TimeTableMapping timeTableMapping = timeTableMappingDAO.findById(id);
        mav.addObject("timeTableMapping",timeTableMapping);

        List<TimeTable> timeTableDetails = timeTableDAO.findAll();
        mav.addObject("timeTables", timeTableDetails);

        List<Subject> subjectDetails = subjectDAO.findAll();
        mav.addObject("subjects", subjectDetails);

        List<StudentGroup> studentGroupDetails = studentGroupDAO.findAll();
        mav.addObject("studentGroups",studentGroupDetails);

        List<Day> dayDetails = dayDAO.findAll();
        mav.addObject("days",dayDetails);

        List<Time> timeDetails = timeDAO.findAll();
        mav.addObject("times",timeDetails);

        return  mav;
    }

    @RequestMapping("/timeTableMapping/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        timeTableDAO.delete(id);
        return  "redirect:/timeTable";
    }
}

