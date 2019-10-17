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
public class RecordsByStudentController {
    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private RecordsByStudentDAO recordsByStudentDAO;



    @RequestMapping("/records-student")
    public String viewHomePage(Model model){
        List<RecordsByStudent> recordsByStudentDetails= recordsByStudentDAO.findAll();
        model.addAttribute("recordsByStudentDetails",recordsByStudentDetails);
        return "recordsByStudent";
    }

    @RequestMapping("/records-student/new")
    public String addRecordsByStudent(Model model){
        RecordsByStudent recordsByStudent = new RecordsByStudent();
        model.addAttribute("recordsByStudent",recordsByStudent);

        List<Student> studentDetail = studentDAO.findAll();
        model.addAttribute("students", studentDetail);

        return "addRecordsByStudent";
    }


    @RequestMapping(value="/records-student/save",method= RequestMethod.POST)
    public String saveRecordsByStudent(@ModelAttribute("recordsByStudent") RecordsByStudent recordsByStudent){
        recordsByStudentDAO.save(recordsByStudent);
        return  "redirect:/records-student";
    }

    @RequestMapping("/records-student/edit/{id}")
    public ModelAndView updateRecordsByStudent(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateRecordsByStudent"));

        RecordsByStudent recordsByStudent=recordsByStudentDAO.findById(id);
        mav.addObject("recordsByStudent",recordsByStudent);

        List<Student> studentDetail = studentDAO.findAll();
        mav.addObject("students", studentDetail);

        return  mav;
    }

    @RequestMapping("/records-student/delete/{id}")
    public String deleteRecordsByStudent(@PathVariable(name="id") Long id){
        recordsByStudentDAO.delete(id);
        return  "redirect:/records-student";
    }

}
