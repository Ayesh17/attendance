package com.project.attendance.controller;

 import com.project.attendance.dao.CourseDAO;
 import com.project.attendance.model.Course;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.servlet.ModelAndView;

 import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;

    @RequestMapping("/course")
    public String viewHomePage(Model model){
        List<Course> courseDetails= courseDAO.findAll();
        model.addAttribute("courseDetails",courseDetails);
        return "course";
    }

    @RequestMapping("/course/new")
    public String addCourse(Model model){
        Course course =new Course();
        model.addAttribute("course",course);
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

        Course course=courseDAO.findById(id);
        mav.addObject("course",course);
        return  mav;
    }

    @RequestMapping("/course/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Long id){
        courseDAO.delete(id);
        return  "redirect:/course";
    }
}
