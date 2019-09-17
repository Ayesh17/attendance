package com.project.attendance.controller;

import com.project.attendance.dao.LectureHallDAO;
import com.project.attendance.dao.MachineDAO;
import com.project.attendance.model.LectureHall;
import com.project.attendance.model.Machine;
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
public class MachineController {

    @Autowired
    private MachineDAO machineDAO;

    @Autowired
    private LectureHallDAO lectureHallDAO;


    @RequestMapping("/machine")
    public String viewHomePage(Model model) {
        List<Machine> machineDetails = machineDAO.findAll();
        model.addAttribute("machineDetails", machineDetails);
        return "machine";
    }

    @RequestMapping("/machine/new")
    public String addMachine(Model model) {
        Machine machine = new Machine();
        model.addAttribute("machine", machine);

        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls", lectureHallDetail);

        return "addMachine";
    }

    @RequestMapping(value = "/machine/save", method = RequestMethod.POST)
    public String saveMachine(@ModelAttribute("machine") Machine machine) {
        machineDAO.save(machine);
        return "redirect:/machine";
    }

    @RequestMapping("/machine/edit/{id}")
    public ModelAndView updateMachine(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView(("updateMachine"));

        Machine machine = machineDAO.findById(id);
        mav.addObject("machine", machine);

        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        mav.addObject("lectureHalls", lectureHallDetail);

        return mav;
    }

    @RequestMapping("/machine/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        machineDAO.delete(id);
        return "redirect:/machine";
    }

}
