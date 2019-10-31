package com.project.attendance.controller;

import com.project.attendance.dao.LectureHallDAO;
import com.project.attendance.dao.MachineDAO;
import com.project.attendance.model.LectureHall;
import com.project.attendance.model.Lecturer;
import com.project.attendance.model.Machine;
import com.project.attendance.util.LecturersCSV;
import com.project.attendance.util.MachinesCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class MachineController {

    @Autowired
    private MachineDAO machineDAO;



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


        return mav;
    }

    @RequestMapping("/machine/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        machineDAO.delete(id);
        return "redirect:/machine";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/machine/upload") // //new annotation since 4.3
    public String machineCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/machine/csv";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            request.setAttribute("path", path);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "forward:/machine/saveAll";
    }



    @RequestMapping(value="/machine/saveAll",method= RequestMethod.POST)
    public String saveAllMachines(@ModelAttribute("machine") Machine machine, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        MachinesCSV csv=new MachinesCSV();
        Path[] args={path} ;
        List<Machine> list = MachinesCSV.main(args);
        try {
            machineDAO.saveAll(list);
        }catch (Exception e){
            System.out.println(e);
        }
        return  "redirect:/machine";
    }

    @RequestMapping("/machine/csv")
    public String view(Model model){
        List<Machine> machineDetails= machineDAO.findAll();
        model.addAttribute("machineDetails",machineDetails);
        return "addAllMachines";
    }

}
