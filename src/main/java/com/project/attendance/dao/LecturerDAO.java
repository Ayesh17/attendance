package com.project.attendance.dao;

import com.project.attendance.model.Lecturer;
import com.project.attendance.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    //to save all
    public void saveAll(List<Lecturer> lecturer){
        lecturerRepository.saveAll(lecturer);
    }

    //to search all students
    public List<Lecturer > findAll(){
        return lecturerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //get a student by id
    public Lecturer  findById(Long id){
        return lecturerRepository.findById(id).orElse(null);
    }

    public Lecturer findBYFIngerId(int fingerId){ return  lecturerRepository.getLecturerByFingerId(fingerId);}


    //delete a student
    public void delete(Long id){
        lecturerRepository.deleteById(id);
    }


}
