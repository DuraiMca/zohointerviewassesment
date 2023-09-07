package com.example.myapplication.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.Viewmodel.UserViewModel
import com.example.myapplication.dao.ATMDao


class ATMOperations : Fragment() {
    lateinit var userViewModel: UserViewModel
    lateinit var checkBalanceButton: Button
    lateinit var withdrawMoneyButton: Button
    lateinit var transferMoneyButton: Button
    lateinit var checkATMBalanceButton: Button
    lateinit var miniStatementButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_a_t_m_operations, container, false)
        checkBalanceButton=view.findViewById(R.id.checkBalanceButton);
        withdrawMoneyButton=view.findViewById(R.id.withdrawMoneyButton);
        transferMoneyButton=view.findViewById(R.id.transferMoneyButton);

        checkATMBalanceButton=view.findViewById(R.id.checkATMBalanceButton);
        miniStatementButton=view.findViewById(R.id.miniStatementButton);
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        withdrawMoneyButton.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_ATMOperations_to_withdrawlFragment)
        })
        transferMoneyButton.setOnClickListener(View.OnClickListener {

        })
        checkATMBalanceButton.setOnClickListener(View.OnClickListener {

        })
        miniStatementButton.setOnClickListener(View.OnClickListener {

        })
        checkBalanceButton.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(context)
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_layout, null)
            val editTextAccountNumber = dialogView.findViewById<EditText>(R.id.editTextAccountNumber)
            val editTextPIN = dialogView.findViewById<EditText>(R.id.editTextPIN)

            builder.setView(dialogView)
                .setPositiveButton("OK") { _, _ ->
                    // Retrieve entered account number and PIN
                    val enteredAccountNumber = editTextAccountNumber.text.toString()
                    val enteredPIN = editTextPIN.text.toString()

                    // Query Room Database to check if the account and PIN match
                    val user = userViewModel.getUserByAccountNumberAndPIN(enteredAccountNumber, enteredPIN)
                    if (user != null) {
                        // Account and PIN match, display balance

                        showBalanceDialog(user.toString())


                    } else {
                        // Account and PIN do not match, show error message
                        showErrorDialog()
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    // User canceled the dialog
                }
                .show()
        })

        return view;
    }

    private fun showBalanceDialog(balance: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Account Balance")
            .setMessage("Your account balance is: $balance")
            .setPositiveButton("OK") { _, _ ->
                // Handle OK button click
            }
            .show()
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
            .setMessage("Invalid account number or PIN.")
            .setPositiveButton("OK") { _, _ ->
                // Handle OK button click
            }
            .show()
    }
}