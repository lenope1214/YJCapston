package com.jumanji.capston.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOperator {
    static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
    static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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
            return YYYYMMDD.format(date);
        }
        return null;
    }

    public static Timestamp strToTimestamp(String string) {
        return java.sql.Timestamp.valueOf(string);
    }

    public static Date strToDate(String string){
        try {
            return YYYYMMDD.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static Timestamp stringToTimestamp(Time time){
////        Timestamp timestamp = Timestamp.valueOf(time);
//        Timestamp timestamp = Timestamp.valueOf(time);
//        System.out.println(Timestamp.(time));
//        return timestamp;
//    }
}
