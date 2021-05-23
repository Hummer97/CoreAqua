package com.xpertwebtech.coreaqua.ui.My_Plan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R

class MyPlanAdapter(var context: Context,var userPlanData: UserPlanData,var clickInterface: RecyclerViewClickInterface):RecyclerView.Adapter<MyPlanAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
            fun bind(position: Int)
            {

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var view:View = inflater.inflate(R.layout.item_subscribe_plan,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 1
    }
}