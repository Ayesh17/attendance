package com.project.attendance.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.project.attendance.model.Enroll;

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
        mapping.put("index_number", "indexNumber");
        mapping.put("year", "year");
        mapping.put("semester", "semester");
        mapping.put("subject_code1", "subjectCode1");
        mapping.put("subject_code2", "subjectCode2");
        mapping.put("subject_code3", "subjectCode3");
        mapping.put("subject_code4", "subjectCode4");
        mapping.put("subject_code5", "subjectCode5");
        mapping.put("subject_code6", "subjectCode6");
        mapping.put("subject_code7", "subjectCode7");
        mapping.put("subject_code8", "subjectCode8");
        mapping.put("subject_code9", "subjectCode9");
        mapping.put("subject_code10", "subjectCode10");
        mapping.put("subject_code11", "subjectCode11");
        mapping.put("subject_code12", "subjectCode12");

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
