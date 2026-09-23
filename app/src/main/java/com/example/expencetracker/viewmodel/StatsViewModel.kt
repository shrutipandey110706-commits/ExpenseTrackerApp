package com.example.expencetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expencetracker.R
import com.example.expencetracker.Utils
import com.example.expencetracker.data.ExpenseDataBase
import com.example.expencetracker.data.dao.ExpenseDao
import com.example.expencetracker.data.madel.ExpenseEntity
import com.example.expencetracker.data.madel.ExpenseSummary
import com.github.mikephil.charting.data.Entry


class StatsViewModel(dao: ExpenseDao) : ViewModel() {

    val entries = dao.getAllExpenesDate()

    fun getEnteriesForChart(entries: List<ExpenseSummary>): List<Entry> {
        val list = mutableListOf<Entry>()

        for (entry in entries) {
            val formattedDate = Utils.getMillisForceDate(entry.date)

            list.add(
                Entry(
                    formattedDate.toFloat(),
                    entry.total_amount.toFloat()
            ))
        }

        return list
    }
}

class StatsViewModelFactor(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            val dao = ExpenseDataBase.getDtabase(context).expenseDao()

            @Suppress("UNCHECKED_CAST")
            return StatsViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}