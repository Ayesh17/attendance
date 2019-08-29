package com.project.attendance.dao;

import com.project.attendance.model.Student;
import com.project.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDAO {

    @Autowired
    StudentRepository studentRepository;

    //to save a student
    public Student save(Student student){
        return studentRepository.save(student);
    }

    //to search all students
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    //get a student by id
    public Student findById(Long id){
        return studentRepository.findById(id).orElse(null);
    }


    //delete a student
    public void delete(Long id){
        studentRepository.deleteById(id);
    }


}
