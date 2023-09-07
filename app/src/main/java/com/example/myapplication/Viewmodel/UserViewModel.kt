package com.example.myapplication.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.ATMDatabase
import com.example.myapplication.Model.ATMTotalAmount
import com.example.myapplication.Model.BankUserDetails
import com.example.myapplication.Repository.ATMRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

     val readAllData:LiveData<List<ATMTotalAmount>>
    val bankUserDetails:LiveData<List<BankUserDetails>>


    private  val repository:ATMRepository

    init {
        val userdao=ATMDatabase.getDatabase(application).userdao()

        repository= ATMRepository(userdao)

        readAllData=repository.getAvailableAmount
        bankUserDetails=repository.getUserDetails

    }

    fun addAmountATM(ATMTotalAmountData:ATMTotalAmount){
      viewModelScope.launch ( Dispatchers.IO){
          repository.insertAmount(ATMTotalAmountData)
      }
    }
    fun addUserDetails(bankUserDetails: BankUserDetails){
        viewModelScope.launch ( Dispatchers.IO){
            repository.insertuserData(bankUserDetails)
        }
    }
    fun deletedetails(){
        viewModelScope.launch ( Dispatchers.IO){
            repository.deleteAllItems()
        }
    }
    fun getUserByAccountNumberAndPIN(accountNumber: String, pin: String){
        viewModelScope.launch ( Dispatchers.IO){
            repository.validateAccount(accountNumber,pin)
        }
    }
    fun getUserBybalance(accountNumber: String, pin: String){
        viewModelScope.launch ( Dispatchers.IO){
            repository.getBankBalance(accountNumber,pin)
        }
    }
}