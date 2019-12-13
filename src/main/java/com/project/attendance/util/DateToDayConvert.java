package com.project.attendance.util;

import com.project.attendance.model.CheckInOut;
import com.project.attendance.model.Records;
import com.project.attendance.model.Time;

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
            Date dateIn = sdf.parse(dateInString);
            System.out.println(dateIn);
          //  String d1=sdf.format(new Date());

            DateToDayConvert obj = new DateToDayConvert();

            //2. Test - Convert Date to Calendar
            Calendar calendar = obj.dateToCalendar(dateIn);
           // System.out.println(calendar.getTime());

            //3. Test - Convert Calendar to Date
            String Day = obj.calendarToDay(calendar);
            String Time=obj.calendarToTime(calendar);
            String Date=obj.calendarToDate(dateInString);
            record.setDay(Day);
            record.setDate(Date);
            record.setTime(Time);
            record.setTimestamp(args[i].getChecktime());
            //System.out.println(newDate);

            records.add(record);

        }

            return records;
    }



    //Convert Date to Calendar
    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println("Date"+date);
        return calendar;

    }

    //Convert Calendar to Day
    private String calendarToDay(Calendar calendar) {
        int day= calendar.getTime().getDay();
        //int time=calendar.getTime().getHours();
       // System.out.println(time);
        switch(day) {
            case 6:
                return "Sunday";
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Satarday";
            default:
                return " ";
        }
    }

    //Convert Calendar to Date
    private String calendarToDate(String str) {
        String[] arr=str.split(" ");
        //String[] spl=str.split(" ");
        //String date= spl[0];
        //int time=calendar.getTime().getHours();
        String date=arr[0];
        return date;
    }

    private String calendarToTime(Calendar calendar) {
        //int day= calendar.getTime().getDay();
        int hour=calendar.getTime().getHours();
        if(hour==0){
            hour=12;
        }
        String hourstr;
        if(hour<10){
            hourstr=0+""+hour;
        }else{
            hourstr=""+hour;
        }
        int min=calendar.getTime().getMinutes();
        String minstr;
        if(min<10){
            minstr=0+""+min;
        }else{
            minstr=""+min;
        }

        String time=hourstr+""+minstr;

        return time;
        // System.out.println(time);

    }

}