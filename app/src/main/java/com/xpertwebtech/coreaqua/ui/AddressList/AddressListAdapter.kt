package com.xpertwebtech.coreaqua.ui.AddressList

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.Interface.RcClickInterfaceWithView
import com.xpertwebtech.coreaqua.R
import kotlinx.android.synthetic.main.item_address_list.view.*

class AddressListAdapter(
    private var context: Context,
    private var userAddress: AddressData,
    private var clickInterface: RcClickInterfaceWithView
) : RecyclerView.Adapter<AddressListAdapter.MyViewHolder>() {
    private var checkedPosition: Int = 0
    private var selectedPosition = -1

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.item_address_list_title.text = userAddress.userAddress?.get(position)?.name
            itemView.item_address_list_mobile_no.text =
                userAddress.userAddress?.get(position)?.phone
            var address: String =
                userAddress.userAddress?.get(position)?.address + ", " + userAddress.userAddress?.get(
                    position
                )?.city + ", " + userAddress.userAddress?.get(position)?.state + ", " + userAddress.userAddress?.get(
                    position
                )?.pin
            itemView.item_address_list_address.text = address
            itemView.item_address_list_curd_btn.setOnClickListener {
                if(!itemView.item_address_list_curd_layout.isVisible)
                {
                    itemView.item_address_list_curd_layout.visibility = View.VISIBLE
                }else {
                    itemView.item_address_list_curd_layout.visibility = View.GONE
                }
            }
            itemView.item_address_list_edit_btn.setOnClickListener {
                //------Edit Activity here
                clickInterface.OnItemClick(position, itemView, adapterPosition,"edit")

            }
            itemView.item_address_list_delete_btn.setOnClickListener {
                //------Delete Activity here
                clickInterface.OnItemClick(position, itemView, adapterPosition,"delete")
            }
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

            if (selectedPosition == position) {
//                            itemView.item_address_list_layout.setBackgroundResource(R.drawable.categoaryrectangle)
                itemView.item_address_list_tick.visibility = View.VISIBLE
            } else {
//                            itemView.item_address_list_layout.setBackgroundResource(R.drawable.rectangle_bg)
                itemView.item_address_list_tick.visibility = View.GONE
            }

            itemView.setOnClickListener {
                if (selectedPosition != adapterPosition) {
                    selectedPosition = adapterPosition
                    notifyDataSetChanged()
                    clickInterface.OnItemClick(position, itemView, adapterPosition,"mainLayout")

//                                userAddress.userAddress?.get(position)?.id?.let1 { it1 -> userAddress.userAddress?.let1 { it2 ->
//                                    calback.onItemClicked(it1,
//                                        it2
//                                    )
//                                } }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.item_address_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return userAddress.userAddress!!.size
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