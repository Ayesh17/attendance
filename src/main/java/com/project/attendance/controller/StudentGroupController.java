package com.project.attendance.controller;

import com.project.attendance.dao.StreamDAO;
import com.project.attendance.dao.StudentGroupDAO;
import com.project.attendance.model.*;
import com.project.attendance.util.StudentGroupsCSV;
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
public class StudentGroupController {
    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private StreamDAO streamDAO;


    @RequestMapping("/studentGroup")
    public String viewHomePage(Model model){
        List<StudentGroup> studentGroupDetails= studentGroupDAO.findAll();
        model.addAttribute("studentGroupDetails",studentGroupDetails);
        return "studentGroup";
    }

    @RequestMapping("/studentGroup/new")
    public String addStudentGroup(Model model){
        StudentGroup studentGroup =new StudentGroup();
        model.addAttribute("studentGroup",studentGroup);

        List<Stream> streamDetail = streamDAO.findAll();
        model.addAttribute("courses", streamDetail);

        return "addStudentGroup";
    }

    @RequestMapping(value="/studentGroup/save",method= RequestMethod.POST)
    public String saveStudentGroup(@ModelAttribute("studentGroup") StudentGroup studentGroup){
        studentGroupDAO.save(studentGroup);
        return  "redirect:/studentGroup";
    }

    @RequestMapping("/studentGroup/edit/{id}")
    public ModelAndView updateStudentGroup(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateStudentGroup"));

        StudentGroup studentGroup = studentGroupDAO.findById(id);
        mav.addObject("studentGroup",studentGroup);

        List<Stream> streamDetail = streamDAO.findAll();
        mav.addObject("courses", streamDetail);

        return  mav;
    }

    @RequestMapping("/studentGroup/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        studentGroupDAO.delete(id);
        return  "redirect:/studentGroup";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/studentGroup/upload") // //new annotation since 4.3
    public String studentGroupCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                          RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/studentGroup/csv";
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


        return "forward:/studentGroup/saveAll";
    }



    @RequestMapping(value="/studentGroup/saveAll",method= RequestMethod.POST)
    public String saveAllMachineMappings(@ModelAttribute("studentGroup") StudentGroup studentGroup, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        StudentGroupsCSV csv=new StudentGroupsCSV();
        Path[] args={path} ;
        List<StudentGroup> list = StudentGroupsCSV.main(args);
        try {
            studentGroupDAO.saveAll(list);
        }catch (Exception e){
            System.out.println(e);
        }
        return  "redirect:/studentGroup";
    }

    @RequestMapping("/studentGroup/csv")
    public String view(Model model){
        List<StudentGroup> studentGroupsDetails= studentGroupDAO.findAll();
        model.addAttribute("studentGroupsDetails",studentGroupsDetails);
        return "addAllStudentGroups";
    }
}
