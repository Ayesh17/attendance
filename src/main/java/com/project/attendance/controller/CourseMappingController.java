package com.project.attendance.controller;

import com.project.attendance.dao.*;
import com.project.attendance.model.*;
import com.project.attendance.util.CourseMappingsCSV;
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
public class CourseMappingController {
    @Autowired
    private CourseMappingDAO courseMappingDAO;

    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StreamDAO streamDAO;

    @Autowired
    private LectureHallDAO lectureHallDAO;


    @RequestMapping("/courseMapping")
    public String viewHomePage(Model model){
        List<CourseMapping> courseMappingDetails = courseMappingDAO.findAll();
        model.addAttribute("courseMappingDetails", courseMappingDetails);
        return "courseMapping";
    }

    @RequestMapping("/courseMapping/new")
    public String addCourseMapping(Model model){
        CourseMapping courseMapping =new CourseMapping();
        model.addAttribute("courseMapping", courseMapping);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        List<Stream> streamDetail = streamDAO.findAll();
        model.addAttribute("streams", streamDetail);

        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        model.addAttribute("studentGroups",studentGroupDetail);

        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls",lectureHallDetail);

        return "addCourseMapping";
    }

    @RequestMapping(value="/courseMapping/save",method= RequestMethod.POST)
    public String saveCourseMapping(@ModelAttribute("courseMapping") CourseMapping courseMapping){
        courseMappingDAO.save(courseMapping);
        return  "redirect:/courseMapping";
    }

    @RequestMapping("/courseMapping/edit/{id}")
    public ModelAndView updateCourseMapping(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateCourseMapping"));

        CourseMapping courseMapping = courseMappingDAO.findById(id);
        mav.addObject("courseMapping", courseMapping);

        List<Course> courseDetail = courseDAO.findAll();
        mav.addObject("courses", courseDetail);
        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        mav.addObject("studentGroups", studentGroupDetail);
        List<Stream> streamDetail = streamDAO.findAll();
        mav.addObject("streams", streamDetail);
        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        mav.addObject("lectureHalls",lectureHallDetail);

        return  mav;
    }

    @RequestMapping("/courseMapping/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        courseMappingDAO.delete(id);
        return  "redirect:/courseMapping";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/courseMapping/upload") // //new annotation since 4.3
    public String courseMappingCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                        RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/courseMapping/csv";
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


        return "forward:/courseMapping/saveAll";
    }



    @RequestMapping(value="/courseMapping/saveAll",method= RequestMethod.POST)
    public String saveAllCourseMappings(@ModelAttribute("courseMapping") CourseMapping courseMapping, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        CourseMappingsCSV csv=new CourseMappingsCSV();
        Path[] args={path} ;
        List<CourseMapping> list = CourseMappingsCSV.main(args);
        try {
            courseMappingDAO.saveAll(list);
        }catch (Exception e){
            System.out.println(e);
        }
        return  "redirect:/courseMapping";
    }

    @RequestMapping("/courseMapping/csv")
    public String view(Model model){
        List<CourseMapping> courseMappingDetails = courseMappingDAO.findAll();
        model.addAttribute("courseMappingDetails", courseMappingDetails);
        return "addAllCourseMappings";
    }
}
