package com.project.attendance.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.project.attendance.model.Course;
import com.project.attendance.model.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsCSV {

    public static List<Student> main(Path[] args)
    {

        Path path=args[0];
        // Hashmap to map CSV data to
        // Bean attributes.
        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("course_code", "courseCode");
        mapping.put("name", "name");
        mapping.put("address", "address");
        mapping.put("nic", "nic");
        mapping.put("registration_number", "registrationNumber");
        mapping.put("user_id", "userId");
        mapping.put("index_number", "indexNumber");

        // HeaderColumnNameTranslateMappingStrategy
        // for Student class
        HeaderColumnNameTranslateMappingStrategy<Student> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Student>();
        strategy.setType(Student.class);
        strategy.setColumnMapping(mapping);

        // Create castobaen and csvreader object
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader
                    (String.valueOf(path)));
        }
        catch (FileNotFoundException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        // call the parse method of CsvToBean
        // pass strategy, csvReader to parse method
        List<Student> list = csvToBean.parse(strategy, csvReader);

        return list;
    }
}
