package com.project.attendance.dao;

import com.project.attendance.model.Student;
import com.project.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    //to save all
    public void saveAll(List<Student> student){
        studentRepository.saveAll(student);
    }

    //to search all students
    public List<Student> findAll(){
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "indexNumber"));
    }

    //get a student by id
    public Student findById(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    //get a student by id
    public List<Student> findByIndexNumber(int indexNumber){
        return studentRepository.findByIndexNumber(indexNumber);
    }

    //get name by indexNumber
    public  String findNameByIndexNumber(int indexNumber){
        List<Student> st=studentRepository.findByIndexNumber(indexNumber);
        return st.get(0).getName();
    }

    //delete a student
    public void delete(Long id){
        studentRepository.deleteById(id);
    }


}
