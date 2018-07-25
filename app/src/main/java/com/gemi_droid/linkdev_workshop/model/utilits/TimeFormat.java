package com.gemi_droid.linkdev_workshop.model.utilits;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormat {

    public static String getCurrentTimeFormat(String publishDate) {
            SimpleDateFormat sdfInPut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat sdfOutPut = new SimpleDateFormat(" MMMM d, yyyy ", new Locale("en"));
        Date dateFrom = null;
        try {
                dateFrom = sdfInPut.parse(publishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdfOutPut.format(dateFrom);
    }
}
