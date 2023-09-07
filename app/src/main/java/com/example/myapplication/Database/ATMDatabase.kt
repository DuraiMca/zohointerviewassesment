package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Model.ATMTotalAmount
import com.example.myapplication.Model.BankUserDetails
import com.example.myapplication.dao.ATMDao

@Database(entities = [ATMTotalAmount::class,BankUserDetails::class], version = 2, exportSchema = false)
abstract class ATMDatabase: RoomDatabase() {
    abstract fun userdao():ATMDao



    companion object{
        @Volatile
        private var INSTANCE:ATMDatabase?=null

        fun getDatabase(context: Context):ATMDatabase{
          val  tempInstance= INSTANCE;
            if(tempInstance!=null){
                return tempInstance;
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,ATMDatabase::class.java,
                    "Atmokok_Database"
                ).build()
                INSTANCE=instance
                return instance;
            }
        }
    }

}