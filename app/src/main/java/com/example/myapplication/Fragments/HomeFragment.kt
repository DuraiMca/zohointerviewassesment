package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.Viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment(){
 lateinit var buttonLoadCash: Button
    lateinit var buttonShowCustomerDetails: Button
    lateinit var buttonShowATMOperations: Button
    //lateinit var recyclerView: RecyclerView
  //  lateinit var userViewModel: UserViewModel
    val adapter=ListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)
        buttonLoadCash=view.findViewById(R.id.buttonLoadCash);
        buttonLoadCash.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        })
        buttonShowCustomerDetails=view.findViewById(R.id.buttonShowCustomerDetails);
        buttonShowCustomerDetails.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_showCustomerDetails)
        })
        buttonShowATMOperations=view.findViewById(R.id.buttonShowATMOperations);
        buttonShowATMOperations.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_ATMOperations)
        })
      //  recyclerView=view.findViewById(R.id.recyler)
     //   recyclerView.adapter=adapter
    //    recyclerView.layoutManager=LinearLayoutManager(requireContext())
//        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
//        userViewModel.readAllData.observe(viewLifecycleOwner,Observer{
//            user->
//             adapter.setData(user)
//        })
        return view;
    }




}