package com.example.myapplication.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Model.ATMTotalAmount
import com.example.myapplication.R
import com.example.myapplication.Viewmodel.UserViewModel


class LoadCashFragment : Fragment() {

  lateinit var submitButton:Button
    lateinit var amount:EditText
    lateinit var count:Spinner
    lateinit var availableAmount:TextView
    var  totalAmount=0;
  lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_add, container, false)
        submitButton=view.findViewById(R.id.loadcash)
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        amount=view.findViewById(R.id.amount)

        count=view.findViewById(R.id.count)
        availableAmount=view.findViewById(R.id.available)
       userViewModel.readAllData.observe(viewLifecycleOwner,
            Observer{
                user->
                var totalSum = 0

                // Loop through the list of ATMTotalAmount objects and add up their TotalAmount values
                for (atmTotalAmount in user) {
                    totalSum += atmTotalAmount.TotalAmount.toInt()
                }
                availableAmount.text="Available amount in ATM "+totalSum+"rupees"
                // Now 'totalSum' contains the sum of all TotalAmount values
                Log.d("TotalSum", "Total Sum: $totalSum+")


        })
        val dataLdist = ArrayList<String>()
        dataLdist.add("2000")
        dataLdist.add("500")
        dataLdist.add("100")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dataLdist)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        count.adapter = spinnerAdapter
        count.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val enteredAmount = amount.text.toString().toIntOrNull() ?: 0

                // Calculate the total amount
                totalAmount = enteredAmount * dataLdist[position].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        submitButton.setOnClickListener(View.OnClickListener {
            val ATMTotalAmount=ATMTotalAmount(0, totalAmount.toLong());
            userViewModel.addAmountATM(ATMTotalAmount);
            Toast.makeText(requireContext(),"Added",Toast.LENGTH_LONG)
        })

     
         return view;
    }



}