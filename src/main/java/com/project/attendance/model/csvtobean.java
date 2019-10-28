package com.project.attendance.model;

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class csvtobean {
    public static List<Subject> main(String[] args)
    {

        // Hashmap to map CSV data to
        // Bean attributes.
        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("name", "name");
        mapping.put("subject_code", "subject_code");

        // HeaderColumnNameTranslateMappingStrategy
        // for Student class
        HeaderColumnNameTranslateMappingStrategy<Subject> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Subject>();
        strategy.setType(Subject.class);
        strategy.setColumnMapping(mapping);

        // Create castobaen and csvreader object
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader
                    ("F:\\subjects.csv"));
        }
        catch (FileNotFoundException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        // call the parse method of CsvToBean
        // pass strategy, csvReader to parse method
        List<Subject> list = csvToBean.parse(strategy, csvReader);

        return list;
    }
}
