package com.example.expencetracker.data.dao
import com.example.expencetracker.data.madel.ExpenseSummary

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.expencetracker.data.madel.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM Expense_Table")
    fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Query("SELECT type, date, SUM(amount) AS total_amount FROM expense_table WHERE type=:type GROUP BY type,date ORDER BY date")
    fun getAllExpenesDate(type: String = "Expense"): Flow<List<ExpenseSummary>>



    @Insert
    suspend fun insertexpense(expenseEntity: ExpenseEntity)


    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updatexpense (expenseEntity: ExpenseEntity)

}