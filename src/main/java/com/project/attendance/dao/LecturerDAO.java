package com.project.attendance.dao;

import com.project.attendance.model.Lecturer;
import com.project.attendance.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LecturerDAO {

    @Autowired
    LecturerRepository lecturerRepository;

    //to save a student
    public Lecturer save(Lecturer lecturer){
        return lecturerRepository.save(lecturer);
    }

    //to search all students
    public List<Lecturer > findAll(){
        return lecturerRepository.findAll();
    }

    //get a student by id
    public Lecturer  findById(Long id){
        return lecturerRepository.findById(id).orElse(null);
    }


    //delete a student
    public void delete(Long id){
        lecturerRepository.deleteById(id);
    }


}
