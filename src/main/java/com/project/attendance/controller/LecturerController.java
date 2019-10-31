package com.project.attendance.controller;

import com.project.attendance.dao.LecturerDAO;
import com.project.attendance.model.Course;
import com.project.attendance.model.Lecturer;
import com.project.attendance.util.CoursesCSV;
import com.project.attendance.util.LecturersCSV;
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

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/lecturer/upload") // //new annotation since 4.3
    public String lecturerCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/lecturer/csv";
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


        return "forward:/lecturer/saveAll";
    }



    @RequestMapping(value="/lecturer/saveAll",method= RequestMethod.POST)
    public String saveAllLecturers(@ModelAttribute("lecturer") Lecturer lecturer, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        LecturersCSV csv=new LecturersCSV();
        Path[] args={path} ;
        List<Lecturer> list = LecturersCSV.main(args);
        try {
            lecturerDAO.saveAll(list);
        }catch(Exception e){
            System.out.println(e);
        }
        return  "redirect:/lecturer";
    }

    @RequestMapping("/lecturer/csv")
    public String view(Model model){
        List<Lecturer> lecturerDetails= lecturerDAO.findAll();
        model.addAttribute("lecturerDetails",lecturerDetails);
        return "addAllLecturers";
    }
}
