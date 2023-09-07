package com.example.myapplication.Repository



import androidx.lifecycle.LiveData
import com.example.myapplication.Model.ATMTotalAmount
import com.example.myapplication.Model.BankUserDetails
import com.example.myapplication.dao.ATMDao

class ATMRepository(private val ATMDao: ATMDao) {


    val getAvailableAmount: LiveData<List<ATMTotalAmount>> = ATMDao.getAvailableAmount()
    val getUserDetails: LiveData<List<BankUserDetails>> = ATMDao.getusers()


    suspend fun insertAmount(ATMTotalAmount:ATMTotalAmount){
        ATMDao.insertData(ATMTotalAmount)
    }
    suspend fun insertuserData(bankUserDetails: BankUserDetails){
        ATMDao.insertuserData(bankUserDetails)
    }
    suspend fun deleteAllItems(){
        ATMDao.deleteAllItems()
    }
    suspend fun validateAccount(accountNumber: String, pin: String): BankUserDetails? {
        return ATMDao.validateAccount(accountNumber, pin)
    }


}