package com.xpertwebtech.coreaqua.ui.AddressList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpertwebtech.coreaqua.Interface.RcClickInterfaceWithView
import com.xpertwebtech.coreaqua.R
import kotlinx.android.synthetic.main.item_address_list.view.*

class AddressListAdapter(private var context: Context, private var clickInterface: RcClickInterfaceWithView):RecyclerView.Adapter<AddressListAdapter.MyViewHolder>() {
    private var checkedPosition:Int = 0
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int)
        {
//            if(checkedPosition == -1)
//            {
//                itemView.item_address_list_layout.visibility = View.GONE
//            }
//            else
//            {
//                if (checkedPosition == adapterPosition)
//                {
//                    itemView.item_address_list_layout.visibility = View.VISIBLE
//                }
//                else
//                {
//                    itemView.item_address_list_layout.visibility = View.GONE
//                }
//            }
            itemView.item_address_list_layout.setOnClickListener {
                clickInterface.OnItemClick(position,itemView)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.item_address_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 4
    }
//
//    public fun SetData(data: ArrayList<AddressData>)
//    {
//        this.data = ArrayList()
//        notifyDataSetChanged()
//    }
//
//    fun getSelected(): AddressData? {
//        return if (checkedPosition !== -1) {
//            data[checkedPosition]
//        } else null
//    }

}