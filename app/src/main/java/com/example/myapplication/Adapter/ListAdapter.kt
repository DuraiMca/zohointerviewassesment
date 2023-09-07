package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.ATMTotalAmount
import com.example.myapplication.Model.BankUserDetails
import com.example.myapplication.R

class ListAdapter:RecyclerView.Adapter<ListAdapter.viewholder>() {

    private var bankUserDetailsList= emptyList<BankUserDetails>()
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
   fun setData(bankUserDetails:List<BankUserDetails>){
       this.bankUserDetailsList=bankUserDetails;
       notifyDataSetChanged()
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
     return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_data,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
    val currentItem=bankUserDetailsList[position]
        holder.itemView.findViewById<TextView>(R.id.txtcount).setText(currentItem.AccNo.toString())
        holder.itemView.findViewById<TextView>(R.id.txtname).setText(currentItem.AccountHolder.toString())
        holder.itemView.findViewById<TextView>(R.id.txtage).setText(currentItem.PinNumber.toString())
        holder.itemView.findViewById<TextView>(R.id.txtamount).setText(currentItem.AccountBalance.toString())
    }

    override fun getItemCount(): Int {
return bankUserDetailsList.size
    }


}