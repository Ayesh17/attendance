package com.project.attendance.controller;

import com.project.attendance.dao.*;
import com.project.attendance.model.*;
import com.project.attendance.util.StudentGroupsCSV;
import com.project.attendance.util.SubjectMappingsCSV;
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
public class SubjectMappingController {
    @Autowired
    private SubjectMappingDAO subjectMappingDAO;

    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private LectureHallDAO lectureHallDAO;


    @RequestMapping("/subjectMapping")
    public String viewHomePage(Model model){
        List<SubjectMapping> subjectMappingDetails= subjectMappingDAO.findAll();
        model.addAttribute("subjectMappingDetails",subjectMappingDetails);
        return "subjectMapping";
    }

    @RequestMapping("/subjectMapping/new")
    public String addSubjectMapping(Model model){
        SubjectMapping subjectMapping =new SubjectMapping();
        model.addAttribute("subjectMapping",subjectMapping);

        List<Subject> subjectDetail = subjectDAO.findAll();
        model.addAttribute("subjects", subjectDetail);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        model.addAttribute("studentGroups",studentGroupDetail);

        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls",lectureHallDetail);

        return "addSubjectMapping";
    }

    @RequestMapping(value="/subjectMapping/save",method= RequestMethod.POST)
    public String saveSubjectMapping(@ModelAttribute("subjectMapping") SubjectMapping subjectMapping){
        subjectMappingDAO.save(subjectMapping);
        return  "redirect:/subjectMapping";
    }

    @RequestMapping("/subjectMapping/edit/{id}")
    public ModelAndView updateSubjectMapping(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateSubjectMapping"));

        SubjectMapping subjectMapping = subjectMappingDAO.findById(id);
        mav.addObject("subjectMapping",subjectMapping);

        List<Subject> subjectDetail = subjectDAO.findAll();
        mav.addObject("subjects", subjectDetail);
        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        mav.addObject("studentGroups", studentGroupDetail);
        List<Course> courseDetail = courseDAO.findAll();
        mav.addObject("courses", courseDetail);
        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        mav.addObject("lectureHalls",lectureHallDetail);

        return  mav;
    }

    @RequestMapping("/subjectMapping/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        subjectMappingDAO.delete(id);
        return  "redirect:/subjectMapping";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/subjectMapping/upload") // //new annotation since 4.3
    public String subjectMappingCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                        RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/subjectMapping/csv";
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


        return "forward:/subjectMapping/saveAll";
    }



    @RequestMapping(value="/subjectMapping/saveAll",method= RequestMethod.POST)
    public String saveAllSubjectMappings(@ModelAttribute("subjectMapping") SubjectMapping subjectMapping, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        SubjectMappingsCSV csv=new SubjectMappingsCSV();
        Path[] args={path} ;
        List<SubjectMapping> list = SubjectMappingsCSV.main(args);
        subjectMappingDAO.saveAll(list);
        return  "redirect:/subjectMapping";
    }

    @RequestMapping("/subjectMapping/csv")
    public String view(Model model){
        List<SubjectMapping> subjectMappingDetails= subjectMappingDAO.findAll();
        model.addAttribute("subjectMappingDetails",subjectMappingDetails);
        return "addAllSubjectMappings";
    }
}
