package com.project.attendance.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.project.attendance.model.StudentGroup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentGroupsCSV {

    public static List<StudentGroup> main(Path[] args)
    {

        Path path=args[0];
        // Hashmap to map CSV data to
        // Bean attributes.
        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("group_code", "groupCode");
        mapping.put("stream_code", "streamCode");
        mapping.put("year", "year");

        // HeaderColumnNameTranslateMappingStrategy
        // for Student class
        HeaderColumnNameTranslateMappingStrategy<StudentGroup> strategy =
                new HeaderColumnNameTranslateMappingStrategy<StudentGroup>();
        strategy.setType(StudentGroup.class);
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
        List<StudentGroup> list = csvToBean.parse(strategy, csvReader);

        return list;
    }
}
