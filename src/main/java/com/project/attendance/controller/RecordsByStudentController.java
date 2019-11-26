package com.project.attendance.controller;

import com.project.attendance.dao.*;
import com.project.attendance.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecordsByStudentController {
    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private RecordsByStudentDAO recordsByStudentDAO;

    @Autowired
    private CourseMappingDAO courseMappingDAO;

    @Autowired
    private RecordsDAO recordsDAO;

    @Autowired
    private EnrollDAO enrollDAO;


    @RequestMapping("/records-student")
    public String viewHomePage(Model model) {
        List<RecordsByStudent> recordsByStudentDetails = recordsByStudentDAO.findAll();
        model.addAttribute("recordsByStudentDetails", recordsByStudentDetails);
        return "recordsByStudent";
    }

    @RequestMapping("/records-student/new")
    public String addRecordsByStudent(Model model) {
        RecordsByStudent recordsByStudent = new RecordsByStudent();
        model.addAttribute("recordsByStudent", recordsByStudent);

        List<Student> studentDetail = studentDAO.findAll();
        model.addAttribute("students", studentDetail);

        return "addRecordsByStudent";
    }



    /*@RequestMapping("/records-student/show/{id}")
    public ModelAndView showRecordsByStudent(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView(("showRecordsByStudent"));

        RecordsByStudent recordsByStudent = recordsByStudentDAO.findById(id);
        List<RecordsByStudent> recordsByStudentList = new ArrayList<>();
        // System.out.println("hey");
        int userId = recordsByStudent.getUserId();
        int year = recordsByStudent.getYear();
        int semester = recordsByStudent.getSemester();
        // System.out.println(recordsByStudent.getId());
        // System.out.println(userId);
        // System.out.println(year);
        //System.out.println(semester);

        //selecet subjects from enrolls if userid,year,semester
        List<Enroll> subjects = enrollDAO.getSubjects(userId, year, semester);

        //get a list of subjectCodes
        List<CourseMapping> subjectMappings = new ArrayList<>();
        //System.out.println("hey2");
        for (int i = 0; i < subjects.size(); i++) {
            RecordsByStudent recordsByStudent1 = new RecordsByStudent();
            recordsByStudent1.setUserId(userId);
            recordsByStudent1.setYear(year);
            recordsByStudent1.setSemester(semester);
            //  System.out.println("hey3");

            if (subjects.get(i).getSubject_code1().isEmpty()) {
                System.out.println("1 is Empty");
            } else {
                System.out.println("New Student");
                CourseMapping subjectMapping1 = new CourseMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code1());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    //   System.out.println("hey4 "+ a);
                    //System.out.println(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        //    System.out.println("hey5");
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();
                        //    System.out.println(day);
                        //   System.out.println("start" + start);
                        //   System.out.println("end" + end);
                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            //       System.out.println("timestamp" + records.get(k).getTimestamp());
                            count++;
                            timetsamp += timetsamp + records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent.setCount(count);
                        recordsByStudent.setTimeStamp(timetsamp);

                    }
                }
                System.out.println("subject" + recordsByStudent.getSubject());
                System.out.println("count" + recordsByStudent.getCount());
                System.out.println("timestamp" + recordsByStudent.getTimeStamp());

                recordsByStudentList.add(recordsByStudent1);
                recordsByStudentDAO.save(recordsByStudent1);
            }


        }


        for (int i = 0; i < recordsByStudentList.size(); i++) {
            mav.addObject("recordsByStudent", recordsByStudentList.get(i));
        }


        //List<Student> studentDetail = studentDAO.findAll();
        //mav.addObject("recordsbystudent", recordsByStudentList);

        return mav;
    }*/


    @RequestMapping(value = "/records-student/save", method = RequestMethod.POST)
    public String saveRecordsByStudent(@ModelAttribute("recordsByStudent") RecordsByStudent recordsByStudent,HttpServletRequest request) {
        int userId = recordsByStudent.getUserId();
        int year = recordsByStudent.getYear();
        int semester = recordsByStudent.getSemester();
        System.out.println(userId);
        System.out.println(year);
        System.out.println(semester);
        List<RecordsByStudent> recordsByStudentList = new ArrayList<>();
        List<Enroll> courses = enrollDAO.getCourses(userId, year, semester);

        //get a list of Course
        List<CourseMapping> courseMappings = new ArrayList<>();
        //System.out.println("hey2");
        for (int i = 0; i < courses.size(); i++) {

             // System.out.println("hey3");

            if (courses.get(i).getCourseCode1().isEmpty()) {
                System.out.println("1 is Empty");
            } else {
                //  System.out.println("New Student");
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode1());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    //   System.out.println("hey4 "+ a);
                    //System.out.println(courseMappings.get(a).getCourseCode());

                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());


                    for (int j = 0; j < courseMappings1.size(); j++) {
                        //    System.out.println("hey5");
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();
                        //    System.out.println(day);
                        //   System.out.println("start" + start);
                        //   System.out.println("end" + end);
                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            //    System.out.println("timestamp" + records.get(k).getTimestamp());
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }

                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }


            }
            if (courses.get(i).getCourseCode2().isEmpty()) {
                System.out.println("2 is Empty");
            } else {
                //  System.out.println("New Student");
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode2());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    //   System.out.println("hey4 "+ a);
                    //System.out.println(courseMappings.get(a).getCoursetCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        //    System.out.println("hey5");
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();
                        //    System.out.println(day);
                        //   System.out.println("start" + start);
                        //   System.out.println("end" + end);
                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            //  System.out.println("timestamp" + records.get(k).getTimestamp());
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }
            if (courses.get(i).getCourseCode3().isEmpty()) {
                System.out.println("3 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode3());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode4().isEmpty()) {
                System.out.println("4 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode4());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode5().isEmpty()) {
                System.out.println("5 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode5());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode6().isEmpty()) {
                System.out.println("6 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode6());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode7().isEmpty()) {
                System.out.println("7 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode7());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode8().isEmpty()) {
                System.out.println("8 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode8());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode9().isEmpty()) {
                System.out.println("9 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode9());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode10().isEmpty()) {
                System.out.println("10 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode10());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode11().isEmpty()) {
                System.out.println("11 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode11());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }if (courses.get(i).getCourseCode12().isEmpty()) {
                System.out.println("12 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                CourseMapping courseMapping1 = new CourseMapping();
                courseMapping1.setCourseCode(courses.get(i).getCourseCode12());
                courseMappings.add(courseMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<CourseMapping> courseMappings1;
                int count = 0;
                for (int a = 0; a < courseMappings.size(); a++) {
                    courseMappings1 = courseMappingDAO.getCourseDetails(courseMappings.get(a).getCourseCode());
                    recordsByStudent1.setCourse(courseMappings.get(a).getCourseCode());

                    for (int j = 0; j < courseMappings1.size(); j++) {
                        String day = courseMappings1.get(j).getDay();
                        int start = courseMappings1.get(j).getStart();
                        int end = courseMappings1.get(j).getEnd();

                        List<Records> records = recordsDAO.getTimestamp(day, start, end);

                        //   System.out.println("hey6");
                        String timetsamp = "";
                        for (int k = 0; k < records.size(); k++) {
                            count++;
                            timetsamp += records.get(k).getTimestamp() + " , ";

                        }
                        recordsByStudent1.setCount(count);
                        recordsByStudent1.setTimeStamp(timetsamp);

                    }
                    recordsByStudentList.add(recordsByStudent1);

                }
            }



        }
        if(recordsByStudentList.isEmpty()){
            System.out.println("Empty List");
            return "recordsByStudentFailed";
        }else {

            try {
                recordsByStudentDAO.saveAll(recordsByStudentList);
            } catch (Exception ex) {
                System.out.println(ex);

            }
            request.setAttribute("userId", userId);
            request.setAttribute("year", year);
            request.setAttribute("semester", semester);
            //System.out.println("hey1.1 "+userId);

            return "forward:/records-student/show";
        }
    }

    @RequestMapping("/records-student/show")
    public String showRecordsByStudent(Model model, HttpServletRequest request) {
       // System.out.println("hey1.2 "+request.getAttribute("userId"));
        int userId = (int) request.getAttribute("userId");
        int year = (int) request.getAttribute("year");
        int semester = (int) request.getAttribute("semester");
        List<RecordsByStudent> recordsByStudentDetails = recordsByStudentDAO.getByUserId(userId,year,semester);
        model.addAttribute("recordsByStudentDetails", recordsByStudentDetails);
        return "showRecordsByStudent";
    }

    @RequestMapping("/records-student/edit/{id}")
    public ModelAndView updateRecordsByStudent(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView(("updateRecordsByStudent"));

        RecordsByStudent recordsByStudent = recordsByStudentDAO.findById(id);
        mav.addObject("recordsByStudent", recordsByStudent);

        List<Student> studentDetail = studentDAO.findAll();
        mav.addObject("students", studentDetail);

        return mav;
    }

    @RequestMapping("/records-student/delete/{id}")
    public String deleteRecordsByStudent(@PathVariable(name = "id") Long id) {
        recordsByStudentDAO.delete(id);
        return "redirect:/records-student";
    }

}
