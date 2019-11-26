package com.project.attendance.dao;

import com.project.attendance.model.Stream;
import com.project.attendance.repository.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamDAO {

    @Autowired
    StreamRepository streamRepository;

    //to save a stream
    public Stream save(Stream stream){
        return streamRepository.save(stream);
    }

    //to save all
    public void saveAll(List<Stream> stream){
        streamRepository.saveAll(stream);
    }

    //to search all streams
    public List<Stream> findAll(){
        return streamRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //get a stream by id
    public Stream findById(Long id){
        return streamRepository.findById(id).orElse(null);
    }


    //delete a stream
    public void delete(Long id){
        streamRepository.deleteById(id);
    }


}
