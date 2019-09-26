package com.project.attendance.controller;

import com.project.attendance.dao.CourseDAO;
import com.project.attendance.dao.LectureHallDAO;
import com.project.attendance.dao.StudentGroupDAO;
import com.project.attendance.dao.SubjectDAO;
import com.project.attendance.model.Course;
import com.project.attendance.model.LectureHall;
import com.project.attendance.model.StudentGroup;
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
public class StudentGroupController {
    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private CourseDAO courseDAO;


    @RequestMapping("/studentGroup")
    public String viewHomePage(Model model){
        List<StudentGroup> studentGroupDetails= studentGroupDAO.findAll();
        model.addAttribute("studentGroupDetails",studentGroupDetails);
        return "studentGroup";
    }

    @RequestMapping("/studentGroup/new")
    public String addStudentGroup(Model model){
        StudentGroup studentGroup =new StudentGroup();
        model.addAttribute("studentGroup",studentGroup);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        return "addStudentGroup";
    }

    @RequestMapping(value="/studentGroup/save",method= RequestMethod.POST)
    public String saveStudentGroup(@ModelAttribute("studentGroup") StudentGroup studentGroup){
        studentGroupDAO.save(studentGroup);
        return  "redirect:/studentGroup";
    }

    @RequestMapping("/studentGroup/edit/{id}")
    public ModelAndView updateStudentGroup(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateStudentGroup"));

        StudentGroup studentGroup = studentGroupDAO.findById(id);
        mav.addObject("studentGroup",studentGroup);

        List<Course> courseDetail = courseDAO.findAll();
        mav.addObject("courses", courseDetail);

        return  mav;
    }

    @RequestMapping("/studentGroup/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        studentGroupDAO.delete(id);
        return  "redirect:/studentGroup";
    }
}
