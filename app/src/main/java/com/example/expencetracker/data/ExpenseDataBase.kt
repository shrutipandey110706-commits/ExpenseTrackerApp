package com.example.expencetracker.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expencetracker.data.madel.ExpenseEntity
import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expencetracker.data.dao.ExpenseDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDataBase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    companion object{
        const val DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getDtabase(context: Context): ExpenseDataBase {
            return Room.databaseBuilder(
                context,
                ExpenseDataBase::class.java,
                DATABASE_NAME
            ).addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }

                fun InitBasicData(context: Context){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao= getDtabase(context).expenseDao()
                        dao.insertexpense(ExpenseEntity(1,"Salary",3000.00,System.currentTimeMillis(),"Salary","Income"))
                        dao.insertexpense(ExpenseEntity(2,"Paypal",200.00,System.currentTimeMillis(),"Paypal","Income"))
                        dao.insertexpense(ExpenseEntity(3,"Netflix",100.00,System.currentTimeMillis(),"Netflix","Expense"))
                        dao.insertexpense(ExpenseEntity(4,"Starbucks",480.00,System.currentTimeMillis(),"Starbucks","Expense"))
                    }

                }
            })
                .build()
        }
    }
}