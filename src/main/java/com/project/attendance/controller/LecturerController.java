package com.project.attendance.controller;

import com.project.attendance.dao.LecturerDAO;
import com.project.attendance.model.Lecturer;
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
public class LecturerController {

    @Autowired
    private LecturerDAO lecturerDAO;

    @RequestMapping("/lecturer")
    public String viewHomePage(Model model){
        List<Lecturer> lecturerDetails=lecturerDAO.findAll();
        model.addAttribute("lecturerDetails",lecturerDetails);
        return "lecturer";
    }

    @RequestMapping("/lecturer/new")
    public String addLecturer(Model model){
        Lecturer lecturer=new Lecturer();
        model.addAttribute("lecturer",lecturer);
        return "addLecturer";
    }

    @RequestMapping(value="/lecturer/save",method= RequestMethod.POST)
    public String saveLecturer(@ModelAttribute("lecturer") Lecturer lecturer){
        lecturerDAO.save(lecturer);
        return  "redirect:/lecturer";
    }

    @RequestMapping("/lecturer/edit/{id}")
    public ModelAndView updateLecturer(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateLecturer"));

        Lecturer lecturer=lecturerDAO.findById(id);
        mav.addObject("lecturer",lecturer);
        return  mav;
    }

    @RequestMapping("/lecturer/delete/{id}")
    public String deleteLecturer(@PathVariable(name="id") Long id){
        lecturerDAO.delete(id);
        return  "redirect:/lecturer";
    }
}
