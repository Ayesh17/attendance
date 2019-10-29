package com.project.attendance.controller;

import com.project.attendance.dao.CourseDAO;
import com.project.attendance.dao.StudentDAO;
import com.project.attendance.model.Course;
import com.project.attendance.model.LectureHall;
import com.project.attendance.model.Student;
import com.project.attendance.util.LectureHallCSV;
import com.project.attendance.util.StudentsCSV;
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
public class StudentEnrollmentController {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;


    @RequestMapping("/student")
    public String viewHomePage(Model model){
        List<Student> studentDetails=studentDAO.findAll();
        model.addAttribute("studentDetails",studentDetails);
        return "student";
    }

    @RequestMapping("/student/new")
    public String addStudent(Model model){
        Student student=new Student();
        model.addAttribute("student",student);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        return "addStudent";
    }

    @RequestMapping(value="/student/save",method= RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student){
        studentDAO.save(student);
        return  "redirect:/student";
    }

    @RequestMapping("/student/edit/{id}")
    public ModelAndView updateStudent(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateStudent"));

        Student student=studentDAO.findById(id);
        mav.addObject("student",student);

        List<Course> courseDetail = courseDAO.findAll();
        mav.addObject("courses", courseDetail);

        return  mav;
    }

    @RequestMapping("/student/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        studentDAO.delete(id);
        return  "redirect:/student";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/student/upload") // //new annotation since 4.3
    public String StudentHallCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                       RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/student/csv";
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


        return "forward:/student/saveAll";
    }



    @RequestMapping(value="/student/saveAll",method= RequestMethod.POST)
    public String saveAllStudents(@ModelAttribute("student") Student student, HttpServletRequest request,RedirectAttributes redirectAttributes){
        Path path = (Path) request.getAttribute("path");
        StudentsCSV csv=new StudentsCSV();
        Path[] args={path} ;
        List<Student> list = StudentsCSV.main(args);
        try {
            studentDAO.saveAll(list);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Duplicate Entry");
            return  "redirect:/student";
        }
        return  "redirect:/student";
    }

    @RequestMapping("/student/csv")
    public String view(Model model){
        List<Student> studentDetails= studentDAO.findAll();
        model.addAttribute("studentDetails",studentDetails);
        return "addAllStudents";
    }
}
