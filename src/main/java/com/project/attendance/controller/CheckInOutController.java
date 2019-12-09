package com.project.attendance.controller;

import com.project.attendance.dao.*;
import com.project.attendance.model.*;
import com.project.attendance.util.DateToDayConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.ParseException;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
public class CheckInOutController {
    @Autowired
    private CheckInOutDAO checkInOutDAO;

    @Autowired
    private RecordsDAO recordsDAO;

    @Autowired
    private LecturerDAO lecturerDAO;

    @Autowired
    private CourseMappingDAO courseMappingDAO;

    @Autowired
    private TimeTableMappingDAO timeTableMappingDAO;

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

        List<Records> finalList= new ArrayList<Records>();

        //initialize a List with  lecture objects
        List<Lecturer> lecturers= new ArrayList<Lecturer>();
        for(int i=0;i<recordsList.size();i++){
            int id= recordsList.get(i).getUserid();

            if(lecturerDAO.findBYFIngerId(id)!=null) {
                System.out.println("lecturerDAO " + lecturerDAO.findBYFIngerId(id).getName());
                lecturers.add(lecturerDAO.findBYFIngerId(id));
            }
            System.out.println("id "+ recordsList.get(i).getUserid());
            System.out.println("day "+ recordsList.get(i).getDay());
            System.out.println("time "+ recordsList.get(i).getTime());
            System.out.println("date "+ recordsList.get(i).getDate());
        }


        //intialize an array with fingerIds of lecturers
        int[] lec=new int[lecturers.size()];
        for(int i=0;i<lecturers.size();i++){
            lec[i]=lecturers.get(i).getFingerId();
        }

        //initialize an array with unique lecture fingerIds
        int[] uniquelec = IntStream.of(lec).distinct().toArray();
        System.out.println("iiiiiiiiiiiiiii ");
        for (int i=0;i<uniquelec.length;i++) {
            System.out.println("i "+ uniquelec[i]);
        }

        //initialize a List with unique lecture objects
        List<Lecturer> uniqueLecturers= new ArrayList<Lecturer>();
        for (int i=0;i<uniquelec.length;i++){
            uniqueLecturers.add(lecturerDAO.findBYFIngerId(uniquelec[i]));
        }

        //get lecturers enrolled subjects
       // Map<Integer,List<CourseMapping>> courseMap = new HashMap<Integer, List<CourseMapping>>();
        String[][] array=new String[uniqueLecturers.size()][];
        for (int i=0;i<uniqueLecturers.size();i++){
            int fingerId=uniqueLecturers.get(i).getFingerId();

            List<CourseMapping> courseList=courseMappingDAO.getCoursesByLecturer(uniqueLecturers.get(i).getCode());
            String[] courseListArr=new String[courseList.size()+1];
            courseListArr[0]=fingerId+"";
            for(int j=0;j<courseList.size();j++){
                courseListArr[j+1]=courseList.get(j).getCourseCode();
            }
            array[i]=courseListArr;
            //courseMap.put(fingerId,courseList);
        }

        /*for (int i=0;i<uniqueLecturers.size();i++){
            for (int j=0;j<array[i].length;j++){
                System.out.print("array "+ array[i][j]);
            }
            System.out.println("sdf");
        }*/

        //get lecturers enrolled subjects start and end also day
        Map<Integer,List<TimeTableMapping>> courseMap = new HashMap<Integer, List<TimeTableMapping>>();
        for(int i=0;i<array.length;i++) {
            int fingerId=Integer.parseInt(array[i][0]);
            String[][] courseListStart=new String[array[i].length-1][3];

            for(int j=0;j<array[i].length;j++) {
                List<TimeTableMapping> courseTimes = timeTableMappingDAO.getTimeTableMappingsByCourseCode(array[i][j]);

                for (TimeTableMapping courseTime: courseTimes) {
                    System.out.println("start "+courseTime.getCourseCode());
                  System.out.println("start "+courseTime.getStart());
                    System.out.println("start "+courseTime.getDay());
                }
                courseMap.put(fingerId,courseTimes);
            }

        }


        //check whether lecturers are between correct timestamp
        for(int i=0;i<lecturers.size();i++){
            List<TimeTableMapping> lists=courseMap.get(lecturers.get(i).getFingerId());
            for (TimeTableMapping list:lists) {
                System.out.println("map "+list.getCourseCode());
            }


        }



        for(int i=0;i<uniqueLecturers.size();i++){
            int fingerId=uniqueLecturers.get(i).getFingerId();
            for(int j=0;j<recordsList.size();j++) {
                int userId = recordsList.get(j).getUserid();
                if(fingerId==userId){
                   // System.out.println("equal"+fingerId+" i "+i+" j "+j);

                }


                try {
                    // recordsDAO.save(recordsList.get(i));
                } catch (Exception ex) {
                    return "records";
                }
            }
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
      System.out.println(record.getDate());
        //recordsDAO.save(record);
        return  "redirect:/records";
    }
     /*
    @RequestMapping("/subject")
    public String viewHomePage(Model model){
        List<Course> subjectDetails= subjectDAO.findAll();
        model.addAttribute("subjectDetails",subjectDetails);
        return "subject";
    }

    @RequestMapping("/subject/new")
    public String addSubject(Model model){
        Course subject =new Course();
        model.addAttribute("subject",subject);

        List<Course> courseDetail = courseDAO.findAll();
        model.addAttribute("courses", courseDetail);


        List<LectureHall> lectureHallDetail = lectureHallDAO.findAll();
        model.addAttribute("lectureHalls",lectureHallDetail);

        return "addSubject";
    }

    @RequestMapping(value="/subject/save",method= RequestMethod.POST)
    public String saveSubject(@ModelAttribute("subject") Course subject){
        subjectDAO.save(subject);
        return  "redirect:/subject";
    }

    @RequestMapping("/subject/edit/{id}")
    public ModelAndView updateSubjcet(@PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView(("updateSubject"));

        Course subject=subjectDAO.findById(id);
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
