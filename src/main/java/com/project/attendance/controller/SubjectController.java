package com.project.attendance.controller;

        import com.project.attendance.dao.CourseDAO;
        import com.project.attendance.dao.LectureHallDAO;
        import com.project.attendance.dao.SubjectDAO;
        import com.project.attendance.model.Course;
        import com.project.attendance.model.LectureHall;
        import com.project.attendance.model.Subject;
        import com.project.attendance.util.SubjectsCSV;
        import com.project.attendance.repository.SubjectRepository;
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
public class SubjectController {
    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private LectureHallDAO lectureHallDAO;

    private final SubjectRepository subjectRepository;



    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F:\\upload\\";

    @GetMapping("/sub")
    public String index() {
        return "addAllSubject";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
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


        return "forward:/subject/saveAll";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        System.out.println("error");
        return "uploadStatus";
    }

    @RequestMapping(value="/subject/saveAll",method= RequestMethod.POST)
    public String saveAllSubjects(@ModelAttribute("subject") Subject subject, HttpServletRequest request){
        Path path = (Path) request.getAttribute("path");
        SubjectsCSV csv=new SubjectsCSV();
        Path[] args={path} ;
        List<Subject> list = SubjectsCSV.main(args);
        subjectDAO.saveAll(list);
        return  "redirect:/subject";
    }


    @RequestMapping("/subject")
    public String viewHomePage(Model model){
        List<Subject> subjectDetails= subjectDAO.findAll();
        model.addAttribute("subjectDetails",subjectDetails);
        return "subject";
    }

    @RequestMapping("/subject/csv")
    public String view(Model model){
        List<Subject> subjectDetails= subjectDAO.findAll();
        model.addAttribute("subjectDetails",subjectDetails);
        return "addAllSubject";
    }

    @RequestMapping("/subject/new")
    public String addSubject(Model model){
        Subject subject =new Subject();
        model.addAttribute("subject",subject);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);


        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls",lectureHallDetail);

        return "addSubject";
    }

    @RequestMapping(value="/subject/save",method= RequestMethod.POST)
    public String saveSubject(@ModelAttribute("subject") Subject subject){
        subjectDAO.save(subject);
        return  "redirect:/subject";
    }



    @RequestMapping("/subject/edit/{id}")
    public ModelAndView updateSubject(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateSubject"));

        Subject subject=subjectDAO.findById(id);
        mav.addObject("subject",subject);

        List<Course> courseDetail = courseDAO.findAll();
        mav.addObject("courses", courseDetail);
        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        mav.addObject("lectureHalls",lectureHallDetail);

        return  mav;
    }

    @RequestMapping("/subject/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        subjectDAO.delete(id);
        return  "redirect:/subject";
    }

}
