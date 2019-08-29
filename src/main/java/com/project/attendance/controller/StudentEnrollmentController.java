package com.project.attendance.controller;

import com.project.attendance.dao.StudentDAO;
import com.project.attendance.model.Student;
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
public class StudentEnrollmentController {

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Student> studentDetails=studentDAO.findAll();
        model.addAttribute("studentDetails",studentDetails);
        return "index";
    }

    @RequestMapping("/new")
    public String addStudent(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return "registerStudent";
    }

    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student){
        studentDAO.save(student);
        return  "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView updateStudent(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateStudent"));

        Student student=studentDAO.findById(id);
        mav.addObject("student",student);
        return  mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        studentDAO.delete(id);
        return  "redirect:/";
    }
}
