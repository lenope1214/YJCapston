package com.jumanji.capston.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOperator {
    public static Date stringToMilisecond(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static String dateToHHMM(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public static String dateToYYYYMMDD(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.format(date);
        }
        return null;
    }

    public static Timestamp strToTimestamp(String string) {
        return java.sql.Timestamp.valueOf(string);
    }

//    public static Timestamp stringToTimestamp(Time time){
////        Timestamp timestamp = Timestamp.valueOf(time);
//        Timestamp timestamp = Timestamp.valueOf(time);
//        System.out.println(Timestamp.(time));
//        return timestamp;
//    }
}
