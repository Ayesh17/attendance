package com.project.attendance.dao;

        import com.project.attendance.model.Machine;
        import com.project.attendance.repository.MachineRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class MachineDAO {

    @Autowired
    MachineRepository machineRepository;

    //to save a machine
    public Machine save(Machine machine){
        return machineRepository.save(machine);
    }

    //to search all machines
    public List<Machine> findAll(){
        return machineRepository.findAll();
    }

    //get a machine by id
    public Machine findById(Long id){
        return machineRepository.findById(id).orElse(null);
    }


    //delete a machine
    public void delete(Long id){
        machineRepository.deleteById(id);
    }
}
