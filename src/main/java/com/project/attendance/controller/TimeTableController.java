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

import java.util.List;

@Controller
public class TimeTableController {
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


    @RequestMapping("/timeTable")
    public String viewHomePage(Model model){
        List<TimeTable> timeTableDetails= timeTableDAO.findAll();
        model.addAttribute("timeTableDetails",timeTableDetails);
        return "timeTable";
    }



    @RequestMapping("/timeTable/new")
        public String addTimeTable(Model model){
        TimeTable timeTable =new TimeTable();
        model.addAttribute("timeTable",timeTable);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("subjects", courseDetail);


        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        model.addAttribute("studentGroups",studentGroupDetail);

        List<Day> dayDetails = dayDAO.findAll();
        model.addAttribute("days",dayDetails);

        List<Time> timeDetails = timeDAO.findAll();
        model.addAttribute("times",timeDetails);

        return "addTimeTable";
    }

    @RequestMapping(value="/timeTable/save",method= RequestMethod.POST)
    public String saveTimeTable(@ModelAttribute("timeTable") TimeTable timeTable){
        timeTableDAO.save(timeTable);
        return  "redirect:/timeTable";
    }

    @RequestMapping(value="/timeTable/saveAll",method= RequestMethod.POST)
    public String saveAll(@ModelAttribute("timeTable") TimeTable timeTable){
        timeTableDAO.saveAll(timeTable);
        return  "redirect:/timeTable";
    }

    @RequestMapping("/timeTable/edit/{id}")
    public ModelAndView updateTimeTable(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateTimeTable"));

        TimeTable timeTable = timeTableDAO.findById(id);
        mav.addObject("timeTable",timeTable);

        List<Course> courseDetails = courseDAO.findAll();
        mav.addObject("subjects", courseDetails);

        List<StudentGroup> studentGroupDetails = studentGroupDAO.findAll();
        mav.addObject("studentGroups",studentGroupDetails);

        return  mav;
    }

    @RequestMapping("/timeTable/map/{id}")
    public String redirect(@PathVariable(name="id") Long id){
        return  "redirect:/timeTableMapping/new";
    }

    @RequestMapping("/timeTable/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        timeTableDAO.delete(id);
        return  "redirect:/timeTable";
    }
}


