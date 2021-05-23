package com.xpertwebtech.coreaqua.ui.WalletHistory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import kotlinx.android.synthetic.main.item_wallet_history.view.*

class WalletHistoryListAdapter(private var context: Context,private var walletListData: WalletListData):RecyclerView.Adapter<WalletHistoryListAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
            fun bind(position:Int)
            {
                var string:String = context.getString(R.string.Rs)
                itemView.item_wallet_history_transaction_id.text = walletListData.userWalletHistory?.get(position)?.paymentRequestId
                itemView.item_wallet_history_status.text = walletListData.userWalletHistory?.get(position)?.paymentStatus
                itemView.item_wallet_history_amount.text =string+" "+walletListData.userWalletHistory?.get(position)?.amount
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)
        var view:View = inflater.inflate(R.layout.item_wallet_history,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return walletListData.userWalletHistory!!.size
    }
}