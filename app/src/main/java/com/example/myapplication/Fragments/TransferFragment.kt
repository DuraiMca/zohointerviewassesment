package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R


class TransferFragment : Fragment() {

    private lateinit var recipientAccountEditText: EditText
    private lateinit var transferAmountEditText: EditText
    private lateinit var transferButton: Button

    private val maxTransferLimit = 10000
    private val minTransferLimit = 1000
    private val senderAccountNumber = "123456" // Replace with actual sender account number
    private val recipientAccountNumber = "789012" // Replace with actual recipient account number

    private var senderAccountBalance = 50000 // Replace with actual sender account balance
    private var recipientAccountBalance = 30000 // Replace with actual recipient account balance
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_transfer, container, false)
        recipientAccountEditText = view.findViewById(R.id.recipientAccountEditText)
        transferAmountEditText = view.findViewById(R.id.transferAmountEditText)
        transferButton = view.findViewById(R.id.transferButton)

        transferButton.setOnClickListener {
            val recipientAccount = recipientAccountEditText.text.toString()
            val transferAmount = transferAmountEditText.text.toString().toIntOrNull()

            if (recipientAccount != recipientAccountNumber) {
                Toast.makeText(context, "Invalid recipient account number", Toast.LENGTH_SHORT).show()
            } else if (transferAmount == null || transferAmount < minTransferLimit || transferAmount > maxTransferLimit) {
                Toast.makeText(
                    context,
                    "Transfer amount must be between $minTransferLimit and $maxTransferLimit",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (senderAccountBalance < transferAmount) {
                Toast.makeText(context, "Insufficient sender account balance", Toast.LENGTH_SHORT).show()
            } else {
                performTransfer(transferAmount)
            }
        }
        return view;
    }
    private fun performTransfer(transferAmount: Int) {
        // Update sender and recipient account balances
        senderAccountBalance -= transferAmount
        recipientAccountBalance += transferAmount

        // Notify the user that the transfer was successful
        Toast.makeText(context, "Transfer successful", Toast.LENGTH_SHORT).show()

        // You can also update the balances in your data storage here
    }


}