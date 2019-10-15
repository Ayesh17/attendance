package com.project.attendance.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateToDayConvert {

    public DateToDayConvert() {
    }

    public static List<Records> main(CheckInOut[] args) throws ParseException {
        System.out.println("hey");
        //1. Create a Date from String

        List<Records> records=new ArrayList<>();;

            for (int i=0;i<args.length;i++) {
            Records record=new Records();
           // System.out.println("i" + args[i]);


            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            record.setUserid(args[i].getUserid());
            String dateInString = args[i].getChecktime();
            Date date = sdf.parse(dateInString);
            DateToDayConvert obj = new DateToDayConvert();

            //2. Test - Convert Date to Calendar
            Calendar calendar = obj.dateToCalendar(date);
           // System.out.println(calendar.getTime());

            //3. Test - Convert Calendar to Date
            String Day = obj.calendarToDate(calendar);
            int Time=obj.calendarToTime(calendar);
            record.setDay(Day);
            record.setTime(Time);
            //System.out.println(newDate);

            records.add(record);

        }

            return records;
    }



    //Convert Date to Calendar
    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

    //Convert Calendar to Day
    private String calendarToDate(Calendar calendar) {
        int day= calendar.getTime().getDay();
        //int time=calendar.getTime().getHours();
       // System.out.println(time);
        switch(day) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Satarday";
            default:
                return "Sunday";
        }
    }

    private int calendarToTime(Calendar calendar) {
        int day= calendar.getTime().getDay();
        int time=calendar.getTime().getHours();
        return time;
        // System.out.println(time);

    }
}