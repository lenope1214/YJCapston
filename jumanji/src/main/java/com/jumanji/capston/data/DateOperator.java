package com.jumanji.capston.data;

import com.jumanji.capston.service.exception.MyNullPointerException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOperator {
    static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddhhmmss");
    static final SimpleDateFormat SYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
    static final SimpleDateFormat SYYYYMMDDHHMMSS = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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

    public static String dateToHHMM(Date date, boolean slash) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");;

        if(date != null){
            if(!slash){ // slash == false
                dateFormat = new SimpleDateFormat("HHmm");
            }
            return dateFormat.format(date);
        }
        throw new MyNullPointerException("dateToHHMM", "Date", "date");
    }

    public static String dateToYYYYMMDD(Date date, boolean slash) {

        if (date != null) {
            if(!slash){
                return YYYYMMDD.format(date);
            }
            return SYYYYMMDD.format(date);
        }
        throw new MyNullPointerException("dateToYYYYMMDD", "Date", "date");
    }

    public static Timestamp strToTimestamp(String string) {
        return java.sql.Timestamp.valueOf(string);
    }

    public static Date strToDate(String string){
        try {
            return SYYYYMMDD.parse(string);
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
