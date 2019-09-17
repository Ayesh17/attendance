package com.project.attendance.dao;

import com.project.attendance.model.Enroll;
import com.project.attendance.model.Machine;
import com.project.attendance.repository.EnrollRepository;
import com.project.attendance.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //to search all enroll records
    public List<Enroll> findAll(){
        return enrollRepository.findAll();
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
