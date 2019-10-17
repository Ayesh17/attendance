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
public class SubjectMappingController {
    @Autowired
    private SubjectMappingDAO subjectMappingDAO;

    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private LectureHallDAO lectureHallDAO;


    @RequestMapping("/subjectMapping")
    public String viewHomePage(Model model){
        List<SubjectMapping> subjectMappingDetails= subjectMappingDAO.findAll();
        model.addAttribute("subjectMappingDetails",subjectMappingDetails);
        return "subjectMapping";
    }

    @RequestMapping("/subjectMapping/new")
    public String addSubjectMapping(Model model){
        SubjectMapping subjectMapping =new SubjectMapping();
        model.addAttribute("subjectMapping",subjectMapping);

        List<Subject> subjectDetail = subjectDAO.findAll();
        model.addAttribute("subjects", subjectDetail);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        model.addAttribute("studentGroups",studentGroupDetail);

        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls",lectureHallDetail);

        return "addSubjectMapping";
    }

    @RequestMapping(value="/subjectMapping/save",method= RequestMethod.POST)
    public String saveSubjectMapping(@ModelAttribute("subjectMapping") SubjectMapping subjectMapping){
        subjectMappingDAO.save(subjectMapping);
        return  "redirect:/subjectMapping";
    }

    @RequestMapping("/subjectMapping/edit/{id}")
    public ModelAndView updateSubjcetMapping(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateSubjectMapping"));

        SubjectMapping subjectMapping = subjectMappingDAO.findById(id);
        mav.addObject("subjectMapping",subjectMapping);

        List<Subject> subjectDetail = subjectDAO.findAll();
        mav.addObject("subjects", subjectDetail);
        List<Course> courseDetail = courseDAO.findAll();
        mav.addObject("courses", courseDetail);
        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        mav.addObject("lectureHalls",lectureHallDetail);

        return  mav;
    }

    @RequestMapping("/subjectMapping/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        subjectMappingDAO.delete(id);
        return  "redirect:/subjectMapping";
    }
}
