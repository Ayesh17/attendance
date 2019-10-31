package com.project.attendance.controller;

import com.project.attendance.dao.LectureHallDAO;
import com.project.attendance.model.Course;
import com.project.attendance.model.LectureHall;
import com.project.attendance.util.CoursesCSV;
import com.project.attendance.util.LectureHallCSV;
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

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/hall/upload") // //new annotation since 4.3
    public String LectureHallCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/hall/csv";
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


        return "forward:/hall/saveAll";
    }



    @RequestMapping(value="/hall/saveAll",method= RequestMethod.POST)
    public String saveAllCourses(@ModelAttribute("course") LectureHall lectureHall, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        LectureHallCSV csv=new LectureHallCSV();
        Path[] args={path} ;
        List<LectureHall> list = LectureHallCSV.main(args);
        try {
            lectureHallDAO.saveAll(list);
        }catch (Exception e){
            System.out.println(e);
        }
        return  "redirect:/hall";
    }

    @RequestMapping("/hall/csv")
    public String view(Model model){
        List<LectureHall> lectureHallDetails= lectureHallDAO.findAll();
        model.addAttribute("lectureHallDetails",lectureHallDetails);
        return "addAllHalls";
    }
}
