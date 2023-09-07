package com.example.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Model.ATMTotalAmount
import com.example.myapplication.Model.BankUserDetails

@Dao
interface ATMDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(ATMTotalAmountData: ATMTotalAmount)
    @Query("Select * from user1_table")
    fun getAvailableAmount():LiveData<List<ATMTotalAmount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertuserData(bankUserDetails: BankUserDetails)
    @Query("Select * from user3_table")
    fun getusers():LiveData<List<BankUserDetails>>

    @Query("DELETE FROM user3_table")
    fun deleteAllItems() // Delete all items in the table
    @Query("SELECT * FROM user3_table WHERE AccNo = :accountNumber AND PinNumber = :pin")
    fun validateAccount(accountNumber: String, pin: String): BankUserDetails?

    @Query("SELECT * FROM user3_table WHERE AccNo = :AccNo AND PinNumber = :PinNumber")
    fun getBalance(AccNo: String, PinNumber: String): BankUserDetails?

}