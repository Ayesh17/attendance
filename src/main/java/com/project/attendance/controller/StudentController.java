package com.project.attendance.controller;

import com.project.attendance.dao.StudentDAO;
import com.project.attendance.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/details")
public class StudentController {

    @Autowired
    StudentDAO studentDAO;

    //save a student to the database
    @PostMapping("/student")
    public Student addStudent(@Valid @RequestBody Student student){
        return studentDAO.save(student);
    }

    //get all students
    @GetMapping("/student")
    public List<Student> getAllStudents(){
        return studentDAO.findAll();
    }

    //get a student by id
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value="id") Long id){

        Student student=studentDAO.findById(id);

        if(student==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(student);
    }

    //update a student
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value="id") Long id,@Valid @RequestBody Student studentDetails){
        Student student=studentDAO.findById(id);

        if(student==null){
            return  ResponseEntity.notFound().build();
        }
        student.setName(studentDetails.getName());
        student.setNic(studentDetails.getNic());
        student.setCourse(studentDetails.getCourse());
        student.setIndexNumber(studentDetails.getIndexNumber());
        student.setRegistrationNumber(studentDetails.getRegistrationNumber());
        student.setUser_id(studentDetails.getUser_id());
        student.setAddress(studentDetails.getAddress());

        Student updateStudent=studentDAO.save(student);
        return ResponseEntity.ok().body(updateStudent);
    }

    //delete a student
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable(value="id") Long id){

        Student student=studentDAO.findById(id);
        if(student==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
