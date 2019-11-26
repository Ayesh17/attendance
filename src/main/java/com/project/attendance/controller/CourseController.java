package com.project.attendance.controller;

        import com.project.attendance.dao.StreamDAO;
        import com.project.attendance.dao.LectureHallDAO;
        import com.project.attendance.dao.CourseDAO;
        import com.project.attendance.model.Course;
        import com.project.attendance.model.Stream;
        import com.project.attendance.model.LectureHall;
        import com.project.attendance.util.CoursesCSV;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.servlet.ModelAndView;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import javax.servlet.http.HttpServletRequest;
        import java.io.*;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StreamDAO streamDAO;

    @Autowired
    private LectureHallDAO lectureHallDAO;




    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @PostMapping("/course/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:course/csv";
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


        return "forward:/course/saveAll";
    }

    @RequestMapping(value="/course/saveAll",method= RequestMethod.POST)
    public String saveAllCourses(@ModelAttribute("course") Course course, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        CoursesCSV csv=new CoursesCSV();
        Path[] args={path} ;
        List<Course> list = CoursesCSV.main(args);
        try {
            courseDAO.saveAll(list);
        }catch(Exception e){
            System.out.println(e);
            return  "redirect:/course";

        }
        return  "redirect:/course";
    }

    @RequestMapping("/course/csv")
    public String view(Model model){
        List<Course> courseDetails = courseDAO.findAll();
        model.addAttribute("courseDetails", courseDetails);
        return "addAllCourses";
    }

    @RequestMapping("/course")
    public String viewHomePage(Model model){
        List<Course> courseDetails = courseDAO.findAll();
        model.addAttribute("courseDetails", courseDetails);
        return "course";
    }



    @RequestMapping("/course/new")
    public String addCourse(Model model){
        Course course =new Course();
        model.addAttribute("course", course);

        List<Stream> streamDetail = streamDAO.findAll();
        model.addAttribute("courses", streamDetail);


        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls",lectureHallDetail);

        return "addCourse";
    }

    @RequestMapping(value="/course/save",method= RequestMethod.POST)
    public String saveCourse(@ModelAttribute("course") Course course){
        courseDAO.save(course);
        return  "redirect:/course";
    }



    @RequestMapping("/course/edit/{id}")
    public ModelAndView updateCourse(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateCourse"));

        Course course = courseDAO.findById(id);
        mav.addObject("course", course);

        List<Stream> streamDetail = streamDAO.findAll();
        mav.addObject("courses", streamDetail);
        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        mav.addObject("lectureHalls",lectureHallDetail);

        return  mav;
    }

    @RequestMapping("/course/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        courseDAO.delete(id);
        return  "redirect:/course";
    }

}
