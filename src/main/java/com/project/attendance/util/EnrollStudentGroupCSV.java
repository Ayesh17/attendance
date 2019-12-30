package com.project.attendance.util;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

public class EnrollStudentGroupCSV {

    public static List<List<String>> main(Path[] args)
    {

        Path path=args[0];
        // Hashmap to map CSV data to
        // Bean attributes.

        List<List<String>> records = new ArrayList<List<String>>();
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader
                    (String.valueOf(path)));
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return records;
    }
}
