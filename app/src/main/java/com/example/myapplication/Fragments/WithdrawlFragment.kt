package com.example.myapplication.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.Viewmodel.UserViewModel


class WithdrawlFragment : Fragment() {

    private lateinit var amountEditText: EditText
    private lateinit var withdrawButton: Button
    lateinit var userViewModel: UserViewModel
    private val maxWithdrawalLimit = 10000
    private val minWithdrawalLimit = 100
    private var accountBalance = 50000 // Replace with actual account balance
    private var atmBalance = 0 // Replace with actual ATM balance
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_withdrawl, container, false)
        amountEditText = view.findViewById(R.id.amountEditText)
        withdrawButton = view.findViewById(R.id.withdrawButton)
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner,
            Observer{
                    user->
                var totalSum = 0

                // Loop through the list of ATMTotalAmount objects and add up their TotalAmount values
                for (atmTotalAmount in user) {
                    totalSum += atmTotalAmount.TotalAmount.toInt()
                }
                atmBalance=totalSum;
                // Now 'totalSum' contains the sum of all TotalAmount values
                Log.d("TotalSum", "Total Sum: $totalSum+")


            })
        withdrawButton.setOnClickListener {
            val withdrawalAmount = amountEditText.text.toString().toIntOrNull()

            if (withdrawalAmount == null) {
                Toast.makeText(context, "Invalid amount", Toast.LENGTH_SHORT).show()
            } else if (withdrawalAmount < minWithdrawalLimit || withdrawalAmount > maxWithdrawalLimit) {
                Toast.makeText(
                    context,
                    "Withdrawal amount must be between $minWithdrawalLimit and $maxWithdrawalLimit",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (atmBalance < withdrawalAmount) {
                Toast.makeText(context, "ATM does not have sufficient funds", Toast.LENGTH_SHORT).show()
            } else if (accountBalance < withdrawalAmount) {
                Toast.makeText(context, "Insufficient account balance", Toast.LENGTH_SHORT).show()
            } else {
                performWithdrawal(withdrawalAmount)
            }
        }
      return  view;
    }

    private fun performWithdrawal(withdrawalAmount: Int) {
        // Implement logic to dispense denominations and update balances
        // You should handle the denominations according to your requirements

        // Deduct withdrawal amount from account balance
        accountBalance -= withdrawalAmount

        // Deduct withdrawal amount from ATM balance
        atmBalance -= withdrawalAmount

        // Update the ATM balance in the local files or database

        // Dispense cash to the user with the appropriate denominations

        Toast.makeText(context, "Withdrawal successful", Toast.LENGTH_SHORT).show()
    }
}