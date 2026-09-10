package com.example.expencetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expencetracker.data.ExpenseDataBase
import com.example.expencetracker.data.dao.ExpenseDao
import com.example.expencetracker.data.madel.ExpenseEntity

class AddExpanseViewModel(val dao: ExpenseDao): ViewModel() {

    suspend fun addExpanse(expanseEntity: ExpenseEntity): Boolean{
        return try {
            dao.insertexpense(expanseEntity)
            true
        }catch (ex: Throwable){
            false
        }
    }


}
class AddExoenseViewModelFactor(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddExpanseViewModel::class.java)) {
            val dao = ExpenseDataBase.getDtabase(context).expenseDao()

            @Suppress("UNCHECKED_CAST")
            return AddExpanseViewModel(dao) as T
            
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

