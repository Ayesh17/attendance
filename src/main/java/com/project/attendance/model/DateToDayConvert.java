package com.project.attendance.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateToDayConvert {

    public DateToDayConvert(String a) {
    }

    public DateToDayConvert() {
    }

    public static void main(String[] args) throws ParseException {
        System.out.println("hey");
        //1. Create a Date from String
        for (int i=0;i<args.length;i++) {
           // System.out.println("i" + args[i]);


            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String dateInString = args[i];
            Date date = sdf.parse(dateInString);
            DateToDayConvert obj = new DateToDayConvert();

            //2. Test - Convert Date to Calendar
            Calendar calendar = obj.dateToCalendar(date);
           // System.out.println(calendar.getTime());

            //3. Test - Convert Calendar to Date
            String newDate = obj.calendarToDate(calendar);
            System.out.println(newDate);

        }
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
        int time=calendar.getTime().getHours();
        System.out.println(time);
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

}