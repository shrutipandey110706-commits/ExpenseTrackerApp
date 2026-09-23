package com.example.expencetracker

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun formatDataToHumanReadableForm(dataInMillies: Long): String {
        val dataFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dataFormatter.format(dataInMillies)
    }

    fun formatDataForChart(dataInMillies: Long): String {
        val dataFormatter = SimpleDateFormat("dd-MMM", Locale.getDefault())
        return dataFormatter.format(dataInMillies)
    }

    fun formatDayMonth(dataInMillies: Long): String {
        val dataFormatter = SimpleDateFormat("dd/MMM", Locale.getDefault())
        return dataFormatter.format(dataInMillies)
    }

    fun formatToDecimalValue(d: Double): String {
        return String.format("%.2f", d)
    }

    fun getMillisForceDate(date: Long): Long {
        return date
    }
}