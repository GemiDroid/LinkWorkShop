package com.gemi_droid.linkdev_workshop.model.utilits

import android.content.Context
import android.content.res.Configuration

import java.util.Locale


object Language {

    fun setLocalization(Lang: String, context: Context) {

        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }

}
