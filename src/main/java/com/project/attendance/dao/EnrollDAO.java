package com.project.attendance.dao;

import com.project.attendance.model.Enroll;
import com.project.attendance.repository.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnrollDAO {
    @Autowired
    EnrollRepository enrollRepository;

    //to save an enroll record
    public Enroll save(Enroll enroll){
        return enrollRepository.save(enroll);
    }

    //to save all
    public void saveAll(List<Enroll> enroll){
        enrollRepository.saveAll(enroll);
    }

    //to search all enroll records
    public List<Enroll> findAll(){
        return enrollRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //to search all enroll records
    public List<Enroll> getCourses(int indexNumber, int year, int semester){
        return enrollRepository.getEnrollByIndexNumberAndYearAndSemester(indexNumber, year, semester);
    }

    //to search enroll records by indexNumber
    public List<Enroll> getCoursesByIndexNumber(int indexNumber){
        return enrollRepository.getEnrollByIndexNumber(indexNumber);
    }


    //get an enroll record by id
    public Enroll findById(Long id){
        return enrollRepository.findById(id).orElse(null);
    }


    //delete an enroll record
    public void delete(Long id){
        enrollRepository.deleteById(id);
    }
}
