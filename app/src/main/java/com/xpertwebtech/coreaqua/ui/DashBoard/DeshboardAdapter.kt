package com.xpertwebtech.coreaqua.ui.DashBoard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import kotlinx.android.synthetic.main.item_water_list.view.*

class DeshboardAdapter(var context: Context,private var productListData: ProductListData,private var clickInterface: RecyclerViewClickInterface):RecyclerView.Adapter<DeshboardAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
            fun bind(position: Int)
            {
                itemView.item_product_list_title.text = productListData.product?.get(position)?.name
                itemView.item_product_list_code.text = productListData.product?.get(position)?.code
                itemView.item_product_list_qty.text = productListData.product?.get(position)?.quantity+" Liters"
                itemView.item_product_list_price.text = "Rs. "+productListData.product?.get(position)?.amount
                Glide.with(itemView.context).load(productListData.product?.get(position)?.url).into(itemView.item_product_list_img)

                itemView.item_product_list_layout.setOnClickListener {
                    clickInterface.OnItemClick(position)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_water_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return productListData.product!!.size
    }
}