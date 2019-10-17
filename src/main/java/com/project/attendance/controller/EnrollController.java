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

import java.sql.SQLException;
import java.util.List;

@Controller
public class EnrollController {
    @Autowired
    private EnrollDAO enrollDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SubjectDAO subjectDAO;


    @RequestMapping("/enroll")
    public String viewHomePage(Model model){
        List<Enroll> enrollDetails= enrollDAO.findAll();
        model.addAttribute("enrollDetails",enrollDetails);
        return "enroll";
    }

    @RequestMapping("/enroll/new")
    public String addEnroll(Model model){
        Enroll enroll =new Enroll();
        model.addAttribute("enroll",enroll);

        List<Student> studentDetail = studentDAO.findAll();
        model.addAttribute("students", studentDetail);


        List<Subject> subjectDetail = subjectDAO.findAll();
        model.addAttribute("subjects",subjectDetail);

        return "addEnroll";
    }

    @RequestMapping(value="/enroll/save",method= RequestMethod.POST)
    public String saveEnroll(@ModelAttribute("enroll") Enroll enroll){

        try{
        enrollDAO.save(enroll);

            List<Enroll> enrollDetails= enrollDAO.findAll();

            for (int i=0;i<enrollDetails.size();i++) {

                int id = enroll.getUserId();
                List<Student> list = studentDAO.findByUserId(id);

                for (int j = 0; j < list.size(); j++) {
                    String name = list.get(j).getName();
                    enroll.setName(name);

                }
                enrollDAO.save(enroll);
            }

        }catch(Exception ex){
            System.out.println(ex);

        }
        return  "redirect:/enroll";
    }

    @RequestMapping("/enroll/edit/{id}")
    public ModelAndView updateEnroll(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateEnroll"));

        Enroll enroll = enrollDAO.findById(id);
        mav.addObject("enroll",enroll);

        List<Student> studentDetails = studentDAO.findAll();
        mav.addObject("students", studentDetails);
        List<Subject> subjectDetails = subjectDAO.findAll();
        mav.addObject("subjects",subjectDetails);


        return  mav;
    }

    @RequestMapping("/enroll/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        enrollDAO.delete(id);
        return  "redirect:/enroll";
    }
}
