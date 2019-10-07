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

    /*
    @RequestMapping ("/timeTableMapping/new")
    public String addTimeTableMapping(Model model) {
        TimeTableMappingDTO timeTablesForm = new TimeTableMappingDTO();
        for (int i = 1; i <= 3; i++) {
            timeTablesForm.addTimeTableMapping(new TimeTableMapping());
        }

        model.addAttribute("form", timeTablesForm);

        return "addTimeTableMapping";
    }
    */


    @RequestMapping(value="/timeTableMapping/saveAll",method= RequestMethod.POST)
    public String saveTimeTable(@ModelAttribute("timeTableMapping") TimeTableMapping timeTableMapping){

        String[] dayArray = timeTableMapping.getDay().split(",");
        String[] subArray = timeTableMapping.getSubject_code().split(",");


        List<TimeTableMapping> tempList = new ArrayList<>();
        for(int i = 0 ; i < dayArray.length; i++) {
            TimeTableMapping tempTimeTable = new TimeTableMapping();
            tempTimeTable.setTime_table_code(timeTableMapping.getTime_table_code());
            System.out.println(timeTableMapping.getTime_table_code());
            tempTimeTable.setDay(dayArray[i]);
            System.out.println(dayArray[i]);
            tempTimeTable.setSubject_code(subArray[i]);
            System.out.println(subArray[i]);
            tempTimeTable.setStart(timeTableMapping.getStart());
            tempTimeTable.setEnd(timeTableMapping.getEnd());
            tempList.add(tempTimeTable);
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


/*

    @RequestMapping(value="/timeTableMapping/saveAll",method= RequestMethod.POST)
    public void saveAll(List<TimeTableMapping> timeTableMappingList) { timeTableMappingDAO.saveAll(timeTableMappingList);}

    @RequestMapping(value="/timeTableMapping/save",method= RequestMethod.POST)
    public String saveTimeTable(@ModelAttribute("timeTableMapping") TimeTableMapping timeTableMapping){
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        System.out.println(timeTableMapping.getId());
        System.out.println(timeTableMapping.getDay());
        System.out.println(timeTableMapping.getStart());
        System.out.println(timeTableMapping.getEnd());
        System.out.println(timeTableMapping.getSubject_code());
        System.out.println(timeTableMapping.getTime_table_code());
        timeTableMappingDAO.save(timeTableMapping);
        return  "redirect:/timeTableMapping/new";
    }
@RequestMapping(value="/timeTableMapping/saveAll",method= RequestMethod.POST) public String saveTimeTable(@ModelAttribute("timeTableMapping") TimeTableMapping timeTableMapping){

String[] dayArray = timeTableMapping.getDay().split(",");
String[] subArray = timeTableMapping.getSubject_code().split(",");


List<TimeTableMapping> tempList = new ArrayList<>();
for(int i = 0 ; i < dayArray.length; i++) {
    TimeTableMapping tempTimeTable = new TimeTableMapping();
    tempTimeTable.setTime_table_code(timeTableMapping.getTime_table_code());
    tempTimeTable.setDay(dayArray[i]);
    tempTimeTable.setSubject_code(subArray[i]);
    tempTimeTable.setStart(timeTableMapping.getStart());
    tempTimeTable.setEnd(timeTableMapping.getEnd());
    tempList.add(tempTimeTable);
}
timeTableMappingDAO.saveAll(tempList);
    return  "redirect:/timeTableMapping";
}
    /*
    @RequestMapping(value="/timeTableMapping/saveAll",method= RequestMethod.POST)
    public String saveAll(@ModelAttribute("timeTableMapping") TimeTableMapping timeTableMapping){
        timeTableDAO.saveAll(timeTableMapping);
        return  "redirect:/timeTableMapping";
    }*/

    @RequestMapping("/timeTableMapping/edit/{id}")
    public ModelAndView updateTimeTable(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateTimeTable"));

        TimeTable timeTable = timeTableDAO.findById(id);
        mav.addObject("timeTable",timeTable);

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

