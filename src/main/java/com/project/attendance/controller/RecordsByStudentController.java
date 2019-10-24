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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private SubjectMappingDAO subjectMappingDAO;

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
        List<SubjectMapping> subjectMappings = new ArrayList<>();
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
                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code1());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
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
        List<Enroll> subjects = enrollDAO.getSubjects(userId, year, semester);

        //get a list of subjectCodes
        List<SubjectMapping> subjectMappings = new ArrayList<>();
        //System.out.println("hey2");
        for (int i = 0; i < subjects.size(); i++) {

            //  System.out.println("hey3");

            if (subjects.get(i).getSubject_code1().isEmpty()) {
                System.out.println("1 is Empty");
            } else {
                //  System.out.println("New Student");
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code1());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    //   System.out.println("hey4 "+ a);
                    //System.out.println(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

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
            if (subjects.get(i).getSubject_code2().isEmpty()) {
                System.out.println("2 is Empty");
            } else {
                //  System.out.println("New Student");
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code2());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    //   System.out.println("hey4 "+ a);
                    //System.out.println(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

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
            if (subjects.get(i).getSubject_code3().isEmpty()) {
                System.out.println("3 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code3());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code4().isEmpty()) {
                System.out.println("4 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code4());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code5().isEmpty()) {
                System.out.println("5 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code5());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code6().isEmpty()) {
                System.out.println("6 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code6());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code7().isEmpty()) {
                System.out.println("7 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code7());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code8().isEmpty()) {
                System.out.println("8 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code8());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code9().isEmpty()) {
                System.out.println("9 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code9());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code10().isEmpty()) {
                System.out.println("10 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code10());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code11().isEmpty()) {
                System.out.println("11 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code11());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
            }if (subjects.get(i).getSubject_code12().isEmpty()) {
                System.out.println("12 is Empty");
            } else {
                RecordsByStudent recordsByStudent1 = new RecordsByStudent();
                recordsByStudent1.setUserId(userId);
                recordsByStudent1.setYear(year);
                recordsByStudent1.setSemester(semester);

                SubjectMapping subjectMapping1 = new SubjectMapping();
                subjectMapping1.setSubjectCode(subjects.get(i).getSubject_code12());
                subjectMappings.add(subjectMapping1);

                //select day and time of subject from subjectmapping if subjectcode
                List<SubjectMapping> subjectMappings1;
                int count = 0;
                for (int a = 0; a < subjectMappings.size(); a++) {
                    subjectMappings1 = subjectMappingDAO.getSubjectDetails(subjectMappings.get(a).getSubjectCode());
                    recordsByStudent1.setSubject(subjectMappings.get(a).getSubjectCode());

                    for (int j = 0; j < subjectMappings1.size(); j++) {
                        String day = subjectMappings1.get(j).getDay();
                        int start = subjectMappings1.get(j).getStart();
                        int end = subjectMappings1.get(j).getEnd();

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
