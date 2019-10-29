package com.project.attendance.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.project.attendance.model.Course;
import com.project.attendance.model.Enroll;
import com.project.attendance.model.SubjectMapping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrollCSV {

    public static List<Enroll> main(Path[] args)
    {

        Path path=args[0];
        // Hashmap to map CSV data to
        // Bean attributes.
        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("user_id", "userId");
        mapping.put("year", "year");
        mapping.put("semester", "semester");
        mapping.put("subject_code1", "subject_code1");
        mapping.put("subject_code2", "subject_code2");
        mapping.put("subject_code3", "subject_code3");
        mapping.put("subject_code4", "subject_code4");
        mapping.put("subject_code5", "subject_code5");
        mapping.put("subject_code6", "subject_code6");
        mapping.put("subject_code7", "subject_code7");
        mapping.put("subject_code8", "subject_code8");
        mapping.put("subject_code9", "subject_code9");

        // HeaderColumnNameTranslateMappingStrategy
        // for Student class
        HeaderColumnNameTranslateMappingStrategy<Enroll> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Enroll>();
        strategy.setType(Enroll.class);
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
        List<Enroll> list = csvToBean.parse(strategy, csvReader);

        return list;
    }
}
