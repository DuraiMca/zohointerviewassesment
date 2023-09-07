package com.example.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.Model.BankUserDetails
import com.example.myapplication.Viewmodel.UserViewModel


class MainActivity : AppCompatActivity(){
    lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
          setupActionBarWithNavController(findNavController(R.id.fragmentContainerView2))

        val userDetails= BankUserDetails(0, 101,"Suresh",2343,25234);
        val userDetails2= BankUserDetails(1, 102,"Ganesh",5432,34123);
        val userDetails3= BankUserDetails(2, 103,"Magesh",7854,26100);
        val userDetails4= BankUserDetails(3, 104,"Naresh",2345,80000);
        val userDetails5= BankUserDetails(4, 105,"Harish",1907,103400);

        userViewModel.addUserDetails(userDetails);
        userViewModel.addUserDetails(userDetails2);
        userViewModel.addUserDetails(userDetails3);
        userViewModel.addUserDetails(userDetails4);
        userViewModel.addUserDetails(userDetails5);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragmentContainerView2)
        return navController.navigateUp()||super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()

            userViewModel.deletedetails()

    }
}