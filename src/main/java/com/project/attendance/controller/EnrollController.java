package com.project.attendance.controller;

import com.project.attendance.dao.*;
import com.project.attendance.model.*;
import com.project.attendance.util.EnrollCSV;
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
import java.sql.SQLException;
import java.util.List;

@Controller
public class EnrollController {
    @Autowired
    private EnrollDAO enrollDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SubjectDAO subjectDAO;


    @RequestMapping("/enroll")
    public String viewHomePage(Model model){
        List<Enroll> enrollDetails= enrollDAO.findAll();
        model.addAttribute("enrollDetails",enrollDetails);
        return "enroll";
    }

    @RequestMapping("/enroll/new")
    public String addEnroll(Model model){
        Enroll enroll =new Enroll();
        model.addAttribute("enroll",enroll);

        List<Student> studentDetail = studentDAO.findAll();
        model.addAttribute("students", studentDetail);


        List<Subject> subjectDetail = subjectDAO.findAll();
        model.addAttribute("subjects",subjectDetail);

        return "addEnroll";
    }

    @RequestMapping(value="/enroll/save",method= RequestMethod.POST)
    public String saveEnroll(@ModelAttribute("enroll") Enroll enroll){

        try{
        enrollDAO.save(enroll);

            List<Enroll> enrollDetails= enrollDAO.findAll();

            for (int i=0;i<enrollDetails.size();i++) {

                int id = enroll.getUserId();
                List<Student> list = studentDAO.findByUserId(id);

                for (int j = 0; j < list.size(); j++) {
                    String name = list.get(j).getName();
                    enroll.setName(name);

                }
               // String str=enrollDetails.get(i).getSubject_code();
                //System.out.println(str);
                enrollDAO.save(enroll);
            }

        }catch(Exception ex){
            System.out.println(ex);

        }
        return  "redirect:/enroll";
    }

    @RequestMapping("/enroll/edit/{id}")
    public ModelAndView updateEnroll(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateEnroll"));

        Enroll enroll = enrollDAO.findById(id);
        mav.addObject("enroll",enroll);

        List<Student> studentDetails = studentDAO.findAll();
        mav.addObject("students", studentDetails);
        List<Subject> subjectDetails = subjectDAO.findAll();
        mav.addObject("subjects",subjectDetails);

        return  mav;
    }

    @RequestMapping("/enroll/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        enrollDAO.delete(id);
        return  "redirect:/enroll";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/enroll/upload") // //new annotation since 4.3
    public String enrollCSVUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                          RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/enroll/csv";
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


        return "forward:/enroll/saveAll";
    }



    @RequestMapping(value="/enroll/saveAll",method= RequestMethod.POST)
    public String saveAllEnrolls(@ModelAttribute("enroll") Enroll enroll, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        EnrollCSV csv=new EnrollCSV();
        Path[] args={path} ;
        List<Enroll> list = EnrollCSV.main(args);

        try{
            enrollDAO.saveAll(list);
            List<Enroll> enrollDetails= list;
            for (int i=0;i<enrollDetails.size();i++) {
                int id = list.get(i).getUserId();
                List<Student> list2 = studentDAO.findByUserId(id);

                for (int j = 0; j < list2.size(); j++) {
                    String name = list2.get(j).getName();
                    enrollDetails.get(i).setName(name);
                }

                enrollDAO.saveAll(enrollDetails);
            }

        }catch(Exception ex){
            System.out.println(ex);

        }

        return  "redirect:/enroll";
    }

    @RequestMapping("/enroll/csv")
    public String view(Model model){
        List<Enroll> enrollDetails= enrollDAO.findAll();
        model.addAttribute("enrollDetails",enrollDetails);
        return "addAllEnrolls";
    }
}
