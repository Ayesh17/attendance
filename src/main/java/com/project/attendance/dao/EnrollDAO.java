package com.project.attendance.dao;

import com.project.attendance.model.Enroll;
import com.project.attendance.repository.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EnrollDAO {
    @Autowired
    EnrollRepository enrollRepository;

    //to save an enroll record
    public Enroll save(Enroll enroll) {
        return enrollRepository.save(enroll);
    }

    //to save all
    public void saveAll(List<Enroll> enroll) {
        enrollRepository.saveAll(enroll);
    }

    //to search all enroll records
    public List<Enroll> findAll() {
        return enrollRepository.findAll(Sort.by(Sort.Direction.ASC, "name", "courseCode"));
    }

    //to search all enroll records
    public List<Enroll> getCourses(int indexNumber, String year) {
        return enrollRepository.getEnrollByIndexNumberAndYear(indexNumber, year);
    }

    //to search enroll records by indexNumber
    public List<Enroll> getCoursesByIndexNumber(int indexNumber) {
        return enrollRepository.getEnrollByIndexNumber(indexNumber);
    }


    //get an enroll record by id
    public Enroll findById(Long id) {
        return enrollRepository.findById(id).orElse(null);
    }

    //get distinct enroll by indexNumber
    public List<Enroll> getDistinct() {
        List<Integer> indexNumberList = enrollRepository.findEnrollDistinctByIndexNumber();

        List<Enroll> enrollList = new ArrayList<Enroll>();
        List<Enroll> editedList = new ArrayList<Enroll>();
        List<Enroll> yearList = new ArrayList<Enroll>();
        for (int i = 0; i < indexNumberList.size(); i++) {
            yearList = enrollRepository.getEnrollByIndexNumber(indexNumberList.get(i));


            Enroll enroll = new Enroll();
            enroll.setIndexNumber(yearList.get(0).getIndexNumber());
            enroll.setYear(yearList.get(0).getYear());
            enroll.setName(yearList.get(0).getName());
            editedList.add(enroll);

            for (int j = 1; j < yearList.size() - 1; j++) {
                //System.out.println("check");
                // System.out.println(yearList.get(j).getYear()+" "+yearList.get(j+1).getYear());

                if (!yearList.get(j).getYear().equals(yearList.get(j + 1).getYear())) {
                    //System.out.println("match");
                    //System.out.println(yearList.get(j).getIndexNumber()+" "+yearList.get(j).getYear());
                    Enroll enroll2 = new Enroll();
                    enroll2.setIndexNumber(yearList.get(j).getIndexNumber());
                    enroll2.setYear(yearList.get(j).getYear());
                    enroll2.setName(yearList.get(j).getName());
                    editedList.add(enroll2);
                }
            }

        }

        for(int i=1;i<editedList.size();i++){
            enrollList.add(editedList.get(i));
        }

        return enrollList;
    }

    //delete an enroll record by indexNumber
    public void deleteByIndexNumber(int indexNumber) {
        List<Enroll> enrollList = enrollRepository.getEnrollByIndexNumber(indexNumber);
        for (Enroll enroll : enrollList) {
            Long id = enroll.getId();
            enrollRepository.deleteById(id);
        }

    }

    //delete an enroll record
    public void delete(Long id) {
        enrollRepository.deleteById(id);
    }


}
