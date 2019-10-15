package com.project.attendance.controller;

import com.project.attendance.dao.CheckInOutDAO;
import com.project.attendance.dao.RecordsDAO;
import com.project.attendance.model.DateToDayConvert;
import com.project.attendance.model.CheckInOut;
import com.project.attendance.model.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.text.ParseException;

@Controller
public class CheckInOutController {
    @Autowired
    private CheckInOutDAO checkInOutDAO;

    @Autowired
    private RecordsDAO recordsDAO;

    @RequestMapping("/records")
    public String read(Model model) throws ParseException {
        System.out.println("hey");
        List<CheckInOut> recordDetails= checkInOutDAO.findAll();
        //System.out.println(recordDetails.toString());

        CheckInOut[] arr = new CheckInOut[recordDetails.size()];
       for(int i = 0 ; i < recordDetails.size(); i++) {
            //System.out.println(recordDetails.get(i).getUserid());
            arr[i]=recordDetails.get(i);
        }
           List<Records> recordsList= DateToDayConvert.main(arr);


        for(int i=0;i<recordsList.size();i++){
            System.out.println("id "+ recordsList.get(i).getUserid());
            System.out.println("day "+ recordsList.get(i).getDay());
            System.out.println("time"+ recordsList.get(i).getTime());
            recordsDAO.save(recordsList.get(i));

        }
        //Records records=new Records();
        model.addAttribute("records",recordsList);
        return "records";
    }



  @RequestMapping(value="/records/save",method= RequestMethod.POST)
    public String saveRecords(@ModelAttribute("record") Records record){
      System.out.println();
      System.out.println(record.getUserid());
      System.out.println(record.getDay());
      System.out.println(record.getTime());
        //recordsDAO.save(record);
        return  "redirect:/records";
    }
     /*
    @RequestMapping("/subject")
    public String viewHomePage(Model model){
        List<Subject> subjectDetails= subjectDAO.findAll();
        model.addAttribute("subjectDetails",subjectDetails);
        return "subject";
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
    public ModelAndView updateSubjcet(@PathVariable(name="id")Long id){
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

     */

}
