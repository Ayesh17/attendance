package com.project.attendance.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.project.attendance.model.CourseMapping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseMappingsCSV {

    public static List<CourseMapping> main(Path[] args)
    {

        Path path=args[0];
        // Hashmap to map CSV data to
        // Bean attributes.
        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("stream_code", "streamCode");
        mapping.put("course_code", "courseCode");
        mapping.put("hall_code", "hallCode");
        mapping.put("group_code", "groupCode");
        mapping.put("semester", "semester");
        mapping.put("year", "year");
        mapping.put("day", "day");
        mapping.put("start", "start");
        mapping.put("end", "end");
        mapping.put("lecturer1Code", "lecturer1Code");
        mapping.put("lecturer2Code", "lecturer2Code");

        // HeaderColumnNameTranslateMappingStrategy
        // for Student class
        HeaderColumnNameTranslateMappingStrategy<CourseMapping> strategy =
                new HeaderColumnNameTranslateMappingStrategy<CourseMapping>();
        strategy.setType(CourseMapping.class);
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
        List<CourseMapping> list = csvToBean.parse(strategy, csvReader);

        return list;
    }
}
