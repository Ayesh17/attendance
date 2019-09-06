package com.project.attendance.controller;

import com.project.attendance.dao.LectureHallDAO;
import com.project.attendance.model.LectureHall;
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
public class LectureHallController {

    @Autowired
    private LectureHallDAO lectureHallDAO;

    @RequestMapping("/hall")
    public String viewHallPage(Model model){
        List<LectureHall> lectureHallDetails=lectureHallDAO.findAll();
        model.addAttribute("lectureHallDetails",lectureHallDetails);
        return "hall";
    }

    @RequestMapping("/hall/new")
    public String addLectureHall(Model model){
        LectureHall lectureHall = new LectureHall();
        model.addAttribute("lectureHall",lectureHall);
        return "addLectureHall";
    }

    @RequestMapping(value="/hall/save",method= RequestMethod.POST)
    public String saveLectureHall(@ModelAttribute("lectureHall") LectureHall lectureHall){
        lectureHallDAO.save(lectureHall);
        return  "redirect:/hall";
    }

    @RequestMapping("/hall/edit/{id}")
    public ModelAndView updateLectureHall(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateLectureHall"));

        LectureHall lectureHall=lectureHallDAO.findById(id);
        mav.addObject("lectureHall",lectureHall);
        return  mav;
    }

    @RequestMapping("/hall/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        lectureHallDAO.delete(id);
        return  "redirect:/hall";
    }
}
