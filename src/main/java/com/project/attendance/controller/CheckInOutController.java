package com.project.attendance.controller;

import com.fasterxml.jackson.databind.util.TypeKey;
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

    @Autowired
    private EnrollDAO enrollDAO;

    @RequestMapping("/records")
    public String read(Model model) throws ParseException {
        System.out.println("hey");
        List<CheckInOut> recordDetails = checkInOutDAO.findAll();
        //System.out.println(recordDetails.toString());

        CheckInOut[] arr = new CheckInOut[recordDetails.size()];
        for (int i = 0; i < recordDetails.size(); i++) {
            //System.out.println(recordDetails.get(i).getUserid());
            arr[i] = recordDetails.get(i);
        }
        List<Records> recordsList = DateToDayConvert.main(arr);



        //initialize a List with  lecture objects
        List<Lecturer> lecturers = new ArrayList<Lecturer>();
        for (int i = 0; i < recordsList.size(); i++) {
            int id = recordsList.get(i).getUserid();

            if (lecturerDAO.findBYFIngerId(id) != null) {
                System.out.println("lecturerDAO " + lecturerDAO.findBYFIngerId(id).getName());
                lecturers.add(lecturerDAO.findBYFIngerId(id));
            }
            System.out.println("id " + recordsList.get(i).getUserid());
            System.out.println("day " + recordsList.get(i).getDay());
            System.out.println("time " + recordsList.get(i).getTime());
            System.out.println("date " + recordsList.get(i).getDate());
        }


        //intialize an array with fingerIds of lecturers
        int[] lec = new int[lecturers.size()];
        for (int i = 0; i < lecturers.size(); i++) {
            lec[i] = lecturers.get(i).getFingerId();
        }

        //initialize an array with unique lecture fingerIds
        int[] uniquelec = IntStream.of(lec).distinct().toArray();
        System.out.println("iiiiiiiiiiiiiii ");
        for (int i = 0; i < uniquelec.length; i++) {
            System.out.println("i " + uniquelec[i]);
        }

        //initialize a List with unique lecture objects
        List<Lecturer> uniqueLecturers = new ArrayList<Lecturer>();
        for (int i = 0; i < uniquelec.length; i++) {
            uniqueLecturers.add(lecturerDAO.findBYFIngerId(uniquelec[i]));
        }

        List<Records> lecturersRecordsList = new ArrayList<Records>();
        for (Records record : recordsList) {
            for (Lecturer ulec : uniqueLecturers) {
                //System.out.println("ulec "+ ulec.getFingerId());
                // System.out.println("id "+ record.getUserid());
                if (record.getUserid() == ulec.getFingerId()) {
                    lecturersRecordsList.add(record);
                    // System.out.println("record fingerId "+ ulec.getFingerId());
                }
            }
        }

        System.out.println("record fingerId ");
        for (Records r : lecturersRecordsList
        ) {

            System.out.println(r.getUserid() + r.getDay() + r.getTime() + r.getDate());

        }

        //get lecturers enrolled subjects
        // Map<Integer,List<CourseMapping>> courseMap = new HashMap<Integer, List<CourseMapping>>();
        String[][] array = new String[uniqueLecturers.size()][];
        for (int i = 0; i < uniqueLecturers.size(); i++) {
            int fingerId = uniqueLecturers.get(i).getFingerId();

            List<CourseMapping> courseList = courseMappingDAO.getCoursesByLecturer(uniqueLecturers.get(i).getCode());
            String[] courseListArr = new String[courseList.size() + 1];
            courseListArr[0] = fingerId + "";
            for (int j = 0; j < courseList.size(); j++) {
                courseListArr[j + 1] = courseList.get(j).getCourseCode();
            }
            array[i] = courseListArr;
            //courseMap.put(fingerId,courseList);
        }

        /*for (int i=0;i<uniqueLecturers.size();i++){
            for (int j=0;j<array[i].length;j++){
                System.out.print("array "+ array[i][j]);
            }
            System.out.println("sdf");
        }*/

        //get lecturers enrolled subjects start and end also day
        Map<Integer, List<TimeTableMapping>> courseMap = new HashMap<Integer, List<TimeTableMapping>>();
        for (int i = 0; i < array.length; i++) {
            int fingerId = Integer.parseInt(array[i][0]);
            String[][] courseListStart = new String[array[i].length - 1][3];

            for (int j = 0; j < array[i].length; j++) {
                List<TimeTableMapping> courseTimes = timeTableMappingDAO.getTimeTableMappingsByCourseCode(array[i][j]);

                for (TimeTableMapping courseTime : courseTimes) {
                    System.out.println("course: " + courseTime.getCourseCode() + " start: " + courseTime.getStart() + " end: " + courseTime.getEnd() + " day: " + courseTime.getDay());
                }
                courseMap.put(fingerId, courseTimes);
            }

        }


        List<LecturersWithStartAndEnd> lecturerRecords = new ArrayList<LecturersWithStartAndEnd>();


        //check whether lecturers are between correct timestamp
        for (int i = 0; i < lecturersRecordsList.size(); i++) {
            //System.out.println("map "+i +" "+lecturers.get(i).getFingerId());
            int time = lecturersRecordsList.get(i).getTime();
            String rday = lecturersRecordsList.get(i).getDay();
            String date = lecturersRecordsList.get(i).getDate();
            int userid = lecturersRecordsList.get(i).getUserid();


            List<TimeTableMapping> lists = courseMap.get(lecturers.get(i).getFingerId());

            System.out.println("finger  " + lecturers.get(i).getFingerId());
            for (TimeTableMapping list : lists) {
                int start = Integer.parseInt(list.getStart());
                if (start < 10) {
                    start = Integer.parseInt("0" + start);
                }
                start = Integer.parseInt(start + "00");
                int end = Integer.parseInt(list.getEnd() + "00");
                String day = list.getDay();

                // System.out.println("course"+list.getCourseCode()+"    day   "+day+"  start1  "+start+"   end1  "+end);


                //System.out.println("agsgfsdasdzda ");
                //System.out.println(day+" "+rday);
                if (day.equals(rday)) {
                    System.out.println("day matched " + list.getDay() + " " + list.getCourseCode());
                    //  System.out.println("time" +time +" start:"+start+" end:"+end);

                    LecturersWithStartAndEnd lecturerRecord = new LecturersWithStartAndEnd();
                    if (time > start && time < end) {
                        //System.out.println("all matched ");
                        System.out.println("all matched " + list.getDay() + " " + list.getCourseCode() + " start:" + list.getStart() + " end:" + list.getEnd());
                        String courseCode=list.getCourseCode();
                        //create a list of lecturers time
                        lecturerRecord.setDay(list.getDay());
                        lecturerRecord.setTime(time + "");
                        lecturerRecord.setDate(date);
                        lecturerRecord.setUserId(userid);
                        lecturerRecord.setCourseCode(courseCode);
                        System.out.println(date);
                        lecturerRecords.add(lecturerRecord);

                    } else {
                        System.out.println("time not matched");
                    }
                } else {
                    System.out.println("day not matched" + list.getDay() + " " + list.getCourseCode());
                }

                // System.out.println("map "+list.getCourseCode());

                //add lecturerRecord to lecturerRecords

            }

        }


        //print lecturerRecord in lecturerRecords
        for (LecturersWithStartAndEnd lecturerRecord : lecturerRecords
        ) {
            System.out.println("lecccccccc");
            System.out.println("day " + lecturerRecord.getDay() + " time " + lecturerRecord.getTime() + " date " + lecturerRecord.getDate() + " userId " + lecturerRecord.getUserId());

        }

        //remove lecturer records if their date is equal and time difference is less than 2 mins
        List<LecturersWithStartAndEnd> editedLecturers = new ArrayList<LecturersWithStartAndEnd>();
        editedLecturers.add(lecturerRecords.get(0));
        int count = -1;
        for (int i = 0; i < lecturerRecords.size(); i++) {

            if (count < lecturerRecords.size() - 2) {
                count++;
            }

            //  System.out.println(lecturerRecords.get(i).getDate());
            if (lecturerRecords.get(count).getDate().equals(lecturerRecords.get(count + 1).getDate())) {
                if (Math.abs(Integer.parseInt(lecturerRecords.get(count).getTime()) - Integer.parseInt(lecturerRecords.get(count + 1).getTime())) < 2) {
                    System.out.println("dateeeeee");
                    //System.out.println(lecturerRecords.get(i).getDate());
                } else {
                    System.out.println("notttttttt");
                    editedLecturers.add(lecturerRecords.get(count + 1));
                }
            } else {
                System.out.println("notttttttt2222222222");
                editedLecturers.add(lecturerRecords.get(count + 1));
                System.out.println("added");
            }

        }

        //print eddited lecturerslist
        System.out.println("size" + editedLecturers.size());
        for (int i = 0; i < editedLecturers.size(); i++) {
            System.out.println("time" + editedLecturers.get(i).getTime() + " " + editedLecturers.get(i).getDate() + " userId " + editedLecturers.get(i).getUserId());
        }



        //important
        //get lecturers start and end times.if no end end=start+1hr
        Map<Integer, List<LecturersWithStartAndEnd>> finalLecturersMap = new HashMap<Integer, List<LecturersWithStartAndEnd>>();
        List<LecturersWithStartAndEnd> finalLecturersList=new ArrayList<LecturersWithStartAndEnd>();

        int count2 = 0;
        for (int i = 0; i < editedLecturers.size()-1; i++) {
            int userId=editedLecturers.get(i).getUserId();
            int flag=0;
            LecturersWithStartAndEnd finalLecturers= new LecturersWithStartAndEnd();
            for(int j=0;j<editedLecturers.size();j++) {

                    if ((editedLecturers.get(i).getUserId() == editedLecturers.get(j).getUserId()) && (editedLecturers.get(i).getDate().equals(editedLecturers.get(j).getDate()))) {
                       //System.out.println("both111 " + editedLecturers.get(i).getTime() + " - " + editedLecturers.get(j).getTime());



                        if ((Math.abs(Integer.parseInt(editedLecturers.get(i).getTime()) - Integer.parseInt(editedLecturers.get(j).getTime())) < 100)&& (Integer.parseInt(editedLecturers.get(i).getTime()) != Integer.parseInt(editedLecturers.get(j).getTime()))){
                            System.out.println("both " + editedLecturers.get(i).getTime() + " - " + editedLecturers.get(j).getTime());

                            finalLecturers.setStart(editedLecturers.get(i).getTime());
                            finalLecturers.setEnd(editedLecturers.get(j).getTime());
                            finalLecturers.setDay(editedLecturers.get(i).getDay());
                            finalLecturers.setDate(editedLecturers.get(i).getDate());
                            finalLecturers.setCourseCode(editedLecturers.get(i).getCourseCode());
                            finalLecturers.setUserId(editedLecturers.get(i).getUserId());
                            finalLecturersList.add(finalLecturers);
                            flag=1;

                        }else{
                            //System.out.println("both2222 " + editedLecturers.get(i).getTime() + " - " + editedLecturers.get(j).getTime());

                            finalLecturers.setStart(editedLecturers.get(i).getTime());
                            finalLecturers.setEnd((Integer.parseInt(editedLecturers.get(i).getTime())+100)+"");
                            finalLecturers.setDay(editedLecturers.get(i).getDay());
                            finalLecturers.setDate(editedLecturers.get(i).getDate());
                            finalLecturers.setCourseCode(editedLecturers.get(i).getCourseCode());
                            finalLecturers.setUserId(editedLecturers.get(i).getUserId());
                        }


                    }

            }
            if(flag==0){
                finalLecturersList.add(finalLecturers);
            }

            finalLecturersMap.put(userId,finalLecturersList);
        }

        int ct=0;
        for (Integer name: finalLecturersMap.keySet()){
            String key = name.toString();
            List<LecturersWithStartAndEnd> value = finalLecturersMap.get(name);
                System.out.println(key + " " +value.get(ct).getDate()+" "+ value.get(ct).getDay()+" "+value.get(ct).getCourseCode()+" "+value.get(ct).getStart()+" "+value.get(ct).getEnd() +" "+value.get(ct).getUserId());
            ct++;

        }

        //get list of students who registered for the same course and signed between lectureres start and end save in the database
        List<Records> finalList = new ArrayList<Records>();
        int counter=0;
        for (Integer name: finalLecturersMap.keySet()){
            String key = name.toString();
            List<LecturersWithStartAndEnd> value = finalLecturersMap.get(name);
            int userId=value.get(counter).getUserId();
            String date=value.get(counter).getDate();
            String day=value.get(counter).getDay();
            String courseCode=value.get(counter).getCourseCode();
            System.out.println("course "+courseCode);
            int start=Integer.parseInt(value.get(counter).getStart());
            int end=Integer.parseInt(value.get(counter).getEnd());
            counter++;
            System.out.println(courseCode+" "+date+" "+start+" "+end+" "+userId);

            for(int i=0;i<recordsList.size();i++){

                if(userId != recordsList.get(i).getUserid()){
                    System.out.println("user "+recordsList.get(i).getUserid());

                      List<Enroll> courseList= enrollDAO.getCoursesByIndexNumber(recordsList.get(i).getUserid());
                      for(Enroll course:courseList){
                          System.out.println(course.getCourseCode());
                          //check whether students enrolled in a course done by the lecturer
                          if(courseCode.equals(course.getCourseCode())){
                              System.out.println("match1 found");
                              //check whether student used fingerprint between 2 fingerprints of lecturer
                              if(recordsList.get(i).getDate().equals(date)&&(recordsList.get(i).getTime()>=start)&&(recordsList.get(i).getTime())<=end){
                                 Records record=new Records();
                                 record.setUserid(recordsList.get(i).getUserid());
                                 record.setDate(recordsList.get(i).getDate());
                                 record.setDay(recordsList.get(i).getDay());
                                 record.setTime(recordsList.get(i).getTime());
                                 record.setTimestamp(recordsList.get(i).getTimestamp());
                                  try {
                                      recordsDAO.save(record);
                                  }catch (Exception e){
                                      System.out.println(e);
                                  }

                                  finalList.add(record);
                                  System.out.println("match found");

                              }
                          }
                      }
                }

            }

        }

        for(int i=0;i<finalList.size();i++){
            System.out.println(finalList.get(i).getUserid()+" "+finalList.get(i).getTimestamp());
        }

        /*
        for (int i = 0; i < uniqueLecturers.size(); i++) {
            int fingerId = uniqueLecturers.get(i).getFingerId();
            for (int j = 0; j < recordsList.size(); j++) {
                int userId = recordsList.get(j).getUserid();
                if (fingerId == userId) {
                    // System.out.println("equal"+fingerId+" i "+i+" j "+j);

                }


                try {
                    // recordsDAO.save(recordsList.get(i));
                } catch (Exception ex) {
                    return "records";
                }
            }
        }
*/

        //Records records=new Records();
        model.addAttribute("records", recordsList);
        return "records";
    }


    @RequestMapping(value = "/records/save", method = RequestMethod.POST)
    public String saveRecords(@ModelAttribute("record") Records record) {
        System.out.println();
        System.out.println(record.getUserid());
        System.out.println(record.getDay());
        System.out.println(record.getTime());
        System.out.println(record.getDate());
        //recordsDAO.save(record);
        return "redirect:/records";
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
