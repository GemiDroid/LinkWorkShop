package com.gemi_droid.linkdev_workshop.model.utilits

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeFormat {

    fun getCurrentTimeFormat(publishDate: String): String {
        val sdfInPut = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val sdfOutPut = SimpleDateFormat(" MMMM d, yyyy ", Locale("en"))
        var dateFrom: Date? = null
        dateFrom = sdfInPut.parse(publishDate)
        return sdfOutPut.format(dateFrom)
    }
}
