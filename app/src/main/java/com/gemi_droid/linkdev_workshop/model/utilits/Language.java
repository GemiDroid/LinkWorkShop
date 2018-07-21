package com.gemi_droid.linkdev_workshop.model.utilits;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;


public class Language {

    public static void setLocalization(String Lang, Context context) {

        Locale locale = new Locale(Lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

}
