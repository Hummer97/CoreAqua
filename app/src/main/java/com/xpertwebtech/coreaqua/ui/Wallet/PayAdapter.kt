package com.xpertwebtech.coreaqua.ui.Wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpertwebtech.coreaqua.R

class PayAdapter(var context: Context):RecyclerView.Adapter<PayAdapter.MyViewHolder>() {
    inner class MyViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int)
        {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var view : View = inflater.inflate(R.layout.item_wallet_payment,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }
}