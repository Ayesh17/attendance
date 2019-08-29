package com.project.attendance.controller;

import com.project.attendance.dao.StudentDAO;
import com.project.attendance.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentEnrollmentController {

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Student> studentDetails=studentDAO.findAll();
        model.addAttribute("studentDetails",studentDetails);
        return "index";
    }
}
