package com.project.attendance.controller;

import com.project.attendance.dao.LectureHallDAO;
import com.project.attendance.dao.MachineDAO;
import com.project.attendance.dao.MachineMappingDAO;
import com.project.attendance.model.LectureHall;
import com.project.attendance.model.Machine;
import com.project.attendance.model.MachineMapping;
import com.project.attendance.util.MachineMappingsCSV;
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

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/machineMapping/upload") // //new annotation since 4.3
    public String machineMappingCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/machineMapping/csv";
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


        return "forward:/machineMapping/saveAll";
    }



    @RequestMapping(value="/machineMapping/saveAll",method= RequestMethod.POST)
    public String saveAllMachineMappings(@ModelAttribute("machine") MachineMapping machineMapping, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        MachineMappingsCSV csv=new MachineMappingsCSV();
        Path[] args={path} ;
        List<MachineMapping> list = MachineMappingsCSV.main(args);
        machineMappingDAO.saveAll(list);
        return  "redirect:/machineMapping";
    }

    @RequestMapping("/machineMapping/csv")
    public String view(Model model){
        List<MachineMapping> machineMappingsDetails= machineMappingDAO.findAll();
        model.addAttribute("machineMappingsDetails",machineMappingsDetails);
        return "addAllMachineMappings";
    }
}
