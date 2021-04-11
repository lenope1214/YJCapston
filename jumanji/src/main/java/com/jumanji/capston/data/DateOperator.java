package com.jumanji.capston.data;

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

    public static String toHHMM(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public static String toYYYYMMDD(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.format(date);
        }
        return null;
    }
}
