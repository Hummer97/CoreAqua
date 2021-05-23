package com.xpertwebtech.coreaqua.ui.DashBoard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpertwebtech.coreaqua.R

class DeshboardBannerAdapter(var context: Context):RecyclerView.Adapter<DeshboardBannerAdapter.MyViewHolder>() {
   inner class MyViewHolder(item: View):RecyclerView.ViewHolder(item) {
            fun bind(position: Int)
            {

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var view:View = inflater.inflate(R.layout.item_banner,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 3
    }
}