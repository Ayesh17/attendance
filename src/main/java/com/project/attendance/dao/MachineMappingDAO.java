package com.project.attendance.dao;

import com.project.attendance.model.MachineMapping;
import com.project.attendance.repository.MachineMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineMappingDAO {

    @Autowired
    MachineMappingRepository machineMappingRepository;

    //to save a machine
    public MachineMapping save(MachineMapping machineMapping){
        return machineMappingRepository.save(machineMapping);
    }

    //to search all machines
    public List<MachineMapping> findAll(){
        return machineMappingRepository.findAll();
    }

    //get a machine by id
    public MachineMapping findById(Long id){
        return machineMappingRepository.findById(id).orElse(null);
    }


    //delete a machine
    public void delete(Long id){
        machineMappingRepository.deleteById(id);
    }
}
