package com.project.attendance.util;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.project.attendance.model.Course;

public class CoursesCSV {
    public static List<Course> main(Path[] args)
    {

        Path path=args[0];
        // Hashmap to map CSV data to
        // Bean attributes.
        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("name", "name");
        mapping.put("course_code", "courseCode");

        // HeaderColumnNameTranslateMappingStrategy
        // for Student class
        HeaderColumnNameTranslateMappingStrategy<Course> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Course>();
        strategy.setType(Course.class);
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
        List<Course> list = csvToBean.parse(strategy, csvReader);

        return list;
    }
}
