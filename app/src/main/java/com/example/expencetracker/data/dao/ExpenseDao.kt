package com.example.expencetracker.data.dao

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

    @Insert
    suspend fun insertexpense(expenseEntity: ExpenseEntity)


    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updatexpense (expenseEntity: ExpenseEntity)

}