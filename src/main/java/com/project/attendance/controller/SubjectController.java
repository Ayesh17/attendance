package com.project.attendance.controller;

import com.project.attendance.dao.CourseDAO;
import com.project.attendance.dao.SubjectDAO;
import com.project.attendance.model.Course;
import com.project.attendance.model.Subject;
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
public class SubjectController {
    @Autowired
    private SubjectDAO subjectDAO;
    private CourseDAO courseDAO;

    @RequestMapping("/subject")
    public String viewHomePage(Model model){
        List<Subject> subjectDetails= subjectDAO.findAll();
        List<Course> courseDetail= courseDAO.findAll();
        model.addAttribute("subjectDetails",subjectDetails);
        model.addAttribute("courses",courseDetail);
        return "subject";
    }

    @RequestMapping("/subject/new")
    public String addSubject(Model model){
        Subject subject =new Subject();
        //Course course=new Course();
        model.addAttribute("subject",subject);
       //model.addAttribute("course",courseDAO.findAll());
        return "addSubject";
    }

    @RequestMapping(value="/subject/save",method= RequestMethod.POST)
    public String saveCourse(@ModelAttribute("subject") Subject subject){
        subjectDAO.save(subject);
        return  "redirect:/subject";
    }

    @RequestMapping("/subject/edit/{id}")
    public ModelAndView updateSubjcet(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateSubject"));

        Subject subject=subjectDAO.findById(id);
        mav.addObject("subject",subject);
        return  mav;
    }

    @RequestMapping("/subject/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        subjectDAO.delete(id);
        return  "redirect:/subject";
    }
}
