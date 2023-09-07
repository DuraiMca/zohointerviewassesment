package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.ListAdapter
import com.example.myapplication.Model.ATMTotalAmount
import com.example.myapplication.Model.BankUserDetails
import com.example.myapplication.R
import com.example.myapplication.Viewmodel.UserViewModel

class ShowCustomerDetails : Fragment() {
    lateinit var userViewModel: UserViewModel
   lateinit var recyclerView: RecyclerView
    val adapter= ListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_show_customer_details, container, false)
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
         // userViewModel.deletedetails()

          recyclerView=view.findViewById(R.id.recyler)
           recyclerView.adapter=adapter
            recyclerView.layoutManager= LinearLayoutManager(requireContext())
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.bankUserDetails.observe(viewLifecycleOwner, Observer{
            user->
             adapter.setData(user)
        })

        return view
    }


}