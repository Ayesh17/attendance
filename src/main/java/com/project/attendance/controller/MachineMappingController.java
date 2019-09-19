package com.project.attendance.controller;

import com.project.attendance.dao.LectureHallDAO;
import com.project.attendance.dao.MachineDAO;
import com.project.attendance.dao.MachineMappingDAO;
import com.project.attendance.model.LectureHall;
import com.project.attendance.model.Machine;
import com.project.attendance.model.MachineMapping;
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
public class MachineMappingController {

    @Autowired
    private MachineMappingDAO machineMappingDAO;

    @Autowired
    private MachineDAO machineDAO;

    @Autowired
    private LectureHallDAO lectureHallDAO;


    @RequestMapping("/machineMapping")
    public String viewHomePage(Model model) {
        List<MachineMapping> machineMappingDetails = machineMappingDAO.findAll();
        model.addAttribute("machineMappingDetails", machineMappingDetails);
        return "machineMapping";
    }

    @RequestMapping("/machineMapping/new")
    public String addMachineMapping(Model model) {
        MachineMapping machineMappingDetail = new MachineMapping();
        model.addAttribute("machineMapping", machineMappingDetail);

        List<Machine> machineDetail = machineDAO.findAll();
        model.addAttribute("machines", machineDetail);

        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls", lectureHallDetail);

        return "addMachineMapping";
    }

    @RequestMapping(value = "/machineMapping/save", method = RequestMethod.POST)
    public String saveMachineMapping(@ModelAttribute("machineMapping") MachineMapping machineMapping) {
        machineMappingDAO.save(machineMapping);
        return "redirect:/machineMapping";
    }

    @RequestMapping("/machineMapping/edit/{id}")
    public ModelAndView updateMachineMapping(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView(("updateMachineMapping"));

        MachineMapping machineMapping = machineMappingDAO.findById(id);
        mav.addObject("machineMapping", machineMapping);

        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        mav.addObject("lectureHalls", lectureHallDetail);

        List<Machine> machineDetail = machineDAO.findAll();
        mav.addObject("machines", machineDetail);

        return mav;
    }

    @RequestMapping("/machineMapping/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        machineMappingDAO.delete(id);
        return "redirect:/machineMapping";
    }

}
