package com.example.myapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user1_table")
data class ATMTotalAmount(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var TotalAmount:Long)