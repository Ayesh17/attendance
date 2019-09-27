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
    private SubjectDAO subjectDAO;

    @Autowired
    private StudentGroupDAO studentGroupDAO;


    @RequestMapping("/timeTable")
    public String viewHomePage(Model model){
        List<TimeTable> timeTableDetails= timeTableDAO.findAll();
        model.addAttribute("timeTableDetails",timeTableDetails);
        return "timeTable";
    }



    @RequestMapping("/timeTable/new")
    public String addTimeTable(Model model, @PathVariable(name="group_code") String group_code){
        TimeTable timeTable =new TimeTable();
        model.addAttribute("timeTable",timeTable);

        List<Subject> subjectDetail = subjectDAO.findAll();
        model.addAttribute("subjects", subjectDetail);


        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        model.addAttribute("studentGroups",studentGroupDetail);

        return "updateTimeTable";
    }

    @RequestMapping(value="/timeTable/save",method= RequestMethod.POST)
    public String saveTimeTable(@ModelAttribute("timeTable") TimeTable timeTable){
        timeTableDAO.save(timeTable);
        return  "redirect:/timeTable";
    }

    @RequestMapping("/timeTable/edit/{id}")
    public ModelAndView updateTimeTable(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateTimeTable"));

        TimeTable timeTable = timeTableDAO.findById(id);
        mav.addObject("timeTable",timeTable);

        List<Subject> subjectDetails = subjectDAO.findAll();
        mav.addObject("subjects", subjectDetails);

        List<StudentGroup> studentGroupDetails = studentGroupDAO.findAll();
        mav.addObject("studentGroups",studentGroupDetails);

        return  mav;
    }

    @RequestMapping("/timeTable/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        timeTableDAO.delete(id);
        return  "redirect:/timeTable";
    }
}


