package com.project.attendance.controller;

import com.project.attendance.dao.*;
import com.project.attendance.model.*;
import com.project.attendance.util.EnrollBatchCSV;
import com.project.attendance.util.EnrollCSV;
import com.project.attendance.util.EnrollStudentGroupCSV;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class EnrollController {
    @Autowired
    private EnrollDAO enrollDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    private CourseDAO courseDAO;


    @RequestMapping("/enroll")
    public String viewHomePage(Model model) {
        List<Enroll> enrollDetails = enrollDAO.getDistinct();


        for (int i = 0; i < enrollDetails.size(); i++) {
            int indexNumber = enrollDetails.get(i).getIndexNumber();
            String year = enrollDetails.get(i).getYear();
            List<Enroll> en = enrollDAO.getCourses(indexNumber, year);
            String courses = "";
            for (int j = 0; j < en.size(); j++) {
                courses = courses + en.get(j).getCourseCode() + "      ";
            }

            enrollDetails.get(i).setCourseCode(courses);

        }
        model.addAttribute("enrollDetails", enrollDetails);
        return "enroll";
    }

    @RequestMapping("/enroll/new")
    public String addEnroll(Model model) {
        Enroll enroll = new Enroll();
        model.addAttribute("enroll", enroll);

        List<Student> studentDetail = studentDAO.findAll();
        model.addAttribute("students", studentDetail);


        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        return "addEnroll";
    }

    @RequestMapping("/enroll/studentGroup")
    public String enrollStudentGroup(Model model) {
        Enroll enroll = new Enroll();
        model.addAttribute("enroll", enroll);

        List<Student> studentDetail = studentDAO.findAll();
        model.addAttribute("students", studentDetail);

        List<StudentGroup> studentGroupDetail = studentGroupDAO.findAll();
        model.addAttribute("studentGroups", studentGroupDetail);


        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);

        return "enrollStudentGroup";
    }


    @RequestMapping(value = "/enroll/save", method = RequestMethod.POST)
    public String saveEnroll(@ModelAttribute("enroll") Enroll enroll) {

        List<Enroll> enrollList = new ArrayList<Enroll>();

        String[] splitArr = enroll.getCourseCode().split(",");
        int count = 0;
        for (int i = 0; i < splitArr.length; i++) {
            if (!splitArr[i].equals(" ")) {
                count++;
            }
        }
        String[] courseCodeArr = new String[count];
        int counter = 0;
        for (int i = 0; i < splitArr.length; i++) {
            if (!splitArr.equals(" ")) {
                courseCodeArr[counter] = splitArr[i];
                counter++;
            }
        }

        for (int i = 0; i < courseCodeArr.length; i++) {

            Enroll enrollItem = new Enroll();
            enrollItem.setIndexNumber(enroll.getIndexNumber());
            enrollItem.setName(studentDAO.findNameByIndexNumber(enroll.getIndexNumber()));
            enrollItem.setYear(enroll.getYear());
            enrollItem.setCourseCode(courseCodeArr[i]);
            enrollList.add(enrollItem);

        }

        for (Enroll en : enrollList
        ) {
            try {
                enrollDAO.save(en);
                // System.out.println(en.getCourseCode()+" "+en.getName()+" "+en.getIndexNumber()+" "+en.getYear());
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }


        return "redirect:/enroll";
    }

    @RequestMapping(value = "/enroll/saveStudentGroup", method = RequestMethod.POST)
    public String saveStudentGroup(@ModelAttribute("enroll") Enroll enroll) {

        List<Enroll> enrollList = new ArrayList<Enroll>();

        Long groupId = enroll.getGroupId();

        StudentGroup studentGroup = studentGroupDAO.findById(groupId);
        String year = studentGroup.getYear();
        String stream = studentGroup.getStreamCode();
        //System.out.println("aaaaaaaaaaa " + year + " " + stream);
        List<Student> studentList = studentDAO.findByStreamAndYear(stream, year);

        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).getName());
        }

        String[] splitArr = enroll.getCourseCode().split(",");
        int count = 0;
        for (int i = 0; i < splitArr.length; i++) {
            System.out.println("arr "+splitArr[i]);
            if (!splitArr[i].equals(" ")) {
                count++;
            }
        }

        String[] courseCodeArr = new String[count];
        int counter = 0;
        for (int i = 0; i < splitArr.length; i++) {
            if (!splitArr.equals(" ")) {
                courseCodeArr[counter] = splitArr[i];
                counter++;
            }
        }

        for (int j = 0; j < studentList.size(); j++) {
            int indexNumber = studentList.get(j).getIndexNumber();
            String name = studentList.get(j).getName();
            for (int i = 0; i < courseCodeArr.length; i++) {

                Enroll enrollItem = new Enroll();
                enrollItem.setIndexNumber(indexNumber);
                enrollItem.setName(name);
                enrollItem.setYear(enroll.getYear());
                enrollItem.setCourseCode(courseCodeArr[i]);

                //System.out.println("i "+i+" j "+j);
               // System.out.println(enrollItem.getIndexNumber()+" "+enrollItem.getCourseCode());
                enrollList.add(enrollItem);

            }
        }

        for (Enroll en : enrollList
        ) {
            try {
                enrollDAO.save(en);
                System.out.println(en.getCourseCode() + " " + en.getName() + " " + en.getIndexNumber() + " " + en.getYear());
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }


        return "redirect:/enroll";
    }

    @RequestMapping("/enroll/edit/{indexNumber}")
    public ModelAndView updateEnroll(@PathVariable(name = "indexNumber") int indexNumber) {
        ModelAndView mav = new ModelAndView(("updateEnroll"));
        List<Enroll> en = enrollDAO.getCoursesByIndexNumber(indexNumber);
        for (int i = 0; i < en.size(); i++) {
            System.out.println(en.get(i).getCourseCode());
        }
        String courseCode1 = en.get(1).getCourseCode();
        mav.addObject("courseCode1", courseCode1);

        mav.addObject("enroll", en.get(0));

        //Enroll enroll = enrollDAO.findById(id);
        // mav.addObject("enroll",enroll);

        List<Student> studentDetails = studentDAO.findAll();
        mav.addObject("students", studentDetails);
        List<Course> courseDetails = courseDAO.findAll();
        mav.addObject("courses", courseDetails);

        return mav;
    }

    @RequestMapping("/enroll/delete/{indexNumber}")
    public String deleteProduct(@PathVariable(name = "indexNumber") int indexNumber) {
        enrollDAO.deleteByIndexNumber(indexNumber);
        return "redirect:/enroll";
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


    @RequestMapping(value = "/enroll/saveAll", method = RequestMethod.POST)
    public String saveAllEnrolls(@ModelAttribute("enroll") Enroll enroll, HttpServletRequest request) {
        Path path = (Path) request.getAttribute("path");
        EnrollCSV csv = new EnrollCSV();
        Path[] args = {path};
        List<Enroll> list = EnrollCSV.main(args);

        try {
            enrollDAO.saveAll(list);
            List<Enroll> enrollDetails = list;
            for (int i = 0; i < enrollDetails.size(); i++) {
                int id = list.get(i).getIndexNumber();
                List<Student> list2 = studentDAO.findByIndexNumber(id);

                for (int j = 0; j < list2.size(); j++) {
                    String name = list2.get(j).getName();
                    enrollDetails.get(i).setName(name);
                }

                enrollDAO.saveAll(enrollDetails);
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }

        return "redirect:/enroll";
    }


    @PostMapping("/enroll/upload2") // //new annotation since 4.3
    public String enrollCSVUpload2(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/enroll/csv2";
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


        return "forward:/enroll/saveAll2";
    }


    @RequestMapping(value = "/enroll/saveAll2", method = RequestMethod.POST)
    public String saveAllEnrolls2(@ModelAttribute("enroll") Enroll enroll, HttpServletRequest request) {
        Path path = (Path) request.getAttribute("path");
        EnrollBatchCSV csv = new EnrollBatchCSV();
        Path[] args = {path};
        List<List<String>> list = EnrollBatchCSV.main(args);

        List<Enroll> enrollList = new ArrayList<>();


        List<String> courseStrList = list.get(7);
        List<String> courseNoList = list.get(8);
        List<String> courseList = new ArrayList<>();
        for (int i = 0; i < courseStrList.size(); i++) {
            courseList.add(courseStrList.get(i) + courseNoList.get(i));
        }


        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i));
        }


        //get the academic year
        System.out.println("year");
        System.out.println(list.get(3));
        String rawYear = list.get(3).get(0);
        String[] yearArr = rawYear.split(" ");
        String year = yearArr[3];
        System.out.println(year);

        for (int i = 10; i < list.size(); i++) {
            for (int j = 5; j < 30; j++) {
                Enroll enrollst = new Enroll();
                System.out.println(i + " " + list.get(i).get(j));
                System.out.println("course" + courseList.get(j));
                if (list.get(i).get(j).equals("1")) {
                    enrollst.setIndexNumber(Integer.parseInt(list.get(i).get(2)));
                    enrollst.setName(list.get(i).get(4));
                    enrollst.setCourseCode(courseList.get(j));
                    enrollst.setYear(year);
                    System.out.println(courseList.get(j));
                    enrollList.add(enrollst);
                } else {
                    System.out.println("No " + list.get(i).get(j));
                }
            }

        }

        for (int i = 0; i < enrollList.size(); i++) {
            System.out.println(" Enroll" + enrollList.get(i).getCourseCode() + " " + enrollList.get(i).getIndexNumber() + " " + enrollList.get(i).getName());

        }
        try {
            enrollDAO.saveAll(enrollList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "redirect:/enroll";
    }

    @PostMapping("/enroll/upload1") // //new annotation since 4.3
    public String enrollCSVUpload1(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/enroll/csv1";
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


        return "forward:/enroll/saveAll1";
    }


    @RequestMapping(value = "/enroll/saveAll1", method = RequestMethod.POST)
    public String saveAllEnrolls1(@ModelAttribute("enroll") Enroll enroll, HttpServletRequest request) {
        Path path = (Path) request.getAttribute("path");
        EnrollBatchCSV csv = new EnrollBatchCSV();
        Path[] args = {path};
        List<List<String>> list = EnrollStudentGroupCSV.main(args);

        List<Enroll> enrollList = new ArrayList<>();


        List<String> courseStrList = list.get(0);
        //List<String> courseNoList = list.get(8);


        //for each row in the csv
        for(int i=1;i<list.size();i++){
        String groupCode=list.get(i).get(0);
            System.out.println("groupCode ");
            //System.out.println(groupCode);
            List<String> courseList=new ArrayList<String>();
            for(int j=2;j<list.get(i).size();j++){
                courseList.add(list.get(i).get(j));
                System.out.println(list.get(i).get(j));
            }


            StudentGroup studentGroup = studentGroupDAO.findByGroupCode(groupCode);
            System.out.println(studentGroup.getGroupCode());
            String enrollyear = studentGroup.getYear();
            String stream = studentGroup.getStreamCode();
            String curYear=list.get(i).get(1);
            System.out.println(enrollyear+" "+stream+" "+curYear);
            List<Student> studentList=studentDAO.findByStreamAndYear(stream,enrollyear);


            //for each student in the relevent studentGroup
            for(int j=0;j<studentList.size();j++){

                //for each course student enrolled to
                for(int k=0;k<courseList.size();k++){
                    System.out.println("course "+courseList.get(k));
                    Enroll enrollStudent=new Enroll();
                    enrollStudent.setName(studentList.get(j).getName());
                    enrollStudent.setIndexNumber(studentList.get(j).getIndexNumber());
                    enrollStudent.setYear(curYear);
                    System.out.println("student "+studentList.get(j).getName());
                    enrollStudent.setCourseCode(courseList.get(k));
                    enrollList.add(enrollStudent);
                }

                try {
                    enrollDAO.saveAll(enrollList);
                }  catch (Exception ex) {
                ex.printStackTrace();
            }

            }



          /*  List<CourseMapping> courseMapping=new ArrayList<CourseMapping>(list.get(i).size());
            for(int j=0;j<list.get(i).size();j++){

            }*/

        }





/*

        List<String> courseList = new ArrayList<>();
        for (int i = 0; i < courseStrList.size(); i++) {
            courseList.add(courseStrList.get(i) + courseNoList.get(i));
        }


        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i));
        }


        //get the academic year
        System.out.println("year");
        System.out.println(list.get(3));
        String rawYear = list.get(3).get(0);
        String[] yearArr = rawYear.split(" ");
        String year = yearArr[3];
        System.out.println(year);

        for (int i = 10; i < list.size(); i++) {
            for (int j = 5; j < 30; j++) {
                Enroll enrollst = new Enroll();
                System.out.println(i + " " + list.get(i).get(j));
                System.out.println("course" + courseList.get(j));
                if (list.get(i).get(j).equals("1")) {
                    enrollst.setIndexNumber(Integer.parseInt(list.get(i).get(2)));
                    enrollst.setName(list.get(i).get(4));
                    enrollst.setCourseCode(courseList.get(j));
                    enrollst.setYear(year);
                    System.out.println(courseList.get(j));
                    enrollList.add(enrollst);
                } else {
                    System.out.println("No " + list.get(i).get(j));
                }
            }

        }

        for (int i = 0; i < enrollList.size(); i++) {
            System.out.println(" Enroll" + enrollList.get(i).getCourseCode() + " " + enrollList.get(i).getIndexNumber() + " " + enrollList.get(i).getName());

        }
        try {
            enrollDAO.saveAll(enrollList);
        } catch (Exception e) {
            System.out.println(e);
        }
*/
        return "redirect:/enroll";
    }


    @RequestMapping("/enroll/csv")
    public String view(Model model) {
        List<Enroll> enrollDetails = enrollDAO.findAll();
        model.addAttribute("enrollDetails", enrollDetails);
        return "addAllEnrolls";
    }

    @RequestMapping("/enroll/csv1")
    public String view1(Model model) {
        List<Enroll> enrollDetails = enrollDAO.findAll();
        model.addAttribute("enrollDetails", enrollDetails);
        return "addAllStudentGroupEnrolls";
    }


    @RequestMapping("/enroll/csv2")
    public String view2(Model model) {
        List<Enroll> enrollDetails = enrollDAO.findAll();
        model.addAttribute("enrollDetails", enrollDetails);
        return "addAllBatchEnrolls";
    }
}
