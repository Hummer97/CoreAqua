package com.xpertwebtech.coreaqua.ui.MyOrders

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import kotlinx.android.synthetic.main.item_order_list.view.*

class OrderListAdapter(private var context: Context,private var orderListData: OrderListData, private var clickInterface: RecyclerViewClickInterface):RecyclerView.Adapter<OrderListAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor")
        fun bind(position:Int)
        {
            itemView.item_order_list_title.text = orderListData.userOrderListing?.get(position)?.orderId
            itemView.item_order_list_quantity.text = orderListData.userOrderListing?.get(position)?.quantity.toString()
            itemView.item_order_list_price.text = orderListData.userOrderListing?.get(position)?.totalAmount.toString()
            var status = orderListData.userOrderListing?.get(position)?.orderStatus
            itemView.item_order_list_status.text = orderListData.userOrderListing?.get(position)?.statusName.toString()
//            if(orderListData.userOrderListing?.get(position)?.statusName.toString() == "Inprocessed")
//            {
//
//            }
            itemView.item_order_list_layout.setOnClickListener {
                clickInterface.OnItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var view:View = inflater.inflate(R.layout.item_order_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return orderListData.userOrderListing!!.size
    }
}