package com.xpertwebtech.coreaqua.ui.Signup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import kotlinx.android.synthetic.main.item_dropdown_list.view.*

class SectorDropdownAdapter(private var context: Context,private var sectorListData: SectorListData,private var clickInterface: RecyclerViewClickInterface):RecyclerView.Adapter<SectorDropdownAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(position:Int)
        {
            itemView.item_dropdown_title.text = sectorListData.sector?.get(position)?.sector
            itemView.item_dropdown_title.setOnClickListener {
                clickInterface.OnItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var v:View = inflater.inflate(R.layout.item_dropdown_list,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return sectorListData.sector!!.size
    }
}