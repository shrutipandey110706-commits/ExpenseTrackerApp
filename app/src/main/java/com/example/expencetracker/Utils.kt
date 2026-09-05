package com.example.expencetracker

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun formatDataToHumanReadableForm(dataInMillies: Long): String {
        val dataFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dataFormatter.format(dataInMillies)
    }
}