package com.example.myapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user3_table")
data class BankUserDetails(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var AccNo:Long,
    var AccountHolder:String,
    var PinNumber:Long,
    var AccountBalance:Long, )