package com.project.attendance.dao;

import com.project.attendance.model.LectureHall;
import com.project.attendance.repository.LectureHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureHallDAO {

    @Autowired
    LectureHallRepository lectureHallRepository;

    //to save a lecture hall
    public LectureHall save(LectureHall lectureHall){
        return lectureHallRepository.save(lectureHall);
    }

    //to save all
    public void saveAll(List<LectureHall> lectureHall){
        lectureHallRepository.saveAll(lectureHall);
    }

    //to search all lecture halls
    public List<LectureHall> findAll(){
        return lectureHallRepository.findAll();
    }

    //get a lecture hall by id
    public LectureHall findById(Long id){
        return lectureHallRepository.findById(id).orElse(null);
    }


    //delete a lecture hall
    public void delete(Long id){
        lectureHallRepository.deleteById(id);
    }


}
