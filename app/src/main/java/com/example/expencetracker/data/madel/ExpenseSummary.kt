package com.example.expencetracker.data.madel

data class ExpenseSummary(
    val type: String,
    val date: Long,
    val total_amount: Double
)