package com.xpertwebtech.coreaqua.ui.WalletHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import kotlinx.android.synthetic.main.fragment_wallet_history.*

class WalletHistoryFragment : BaseFragment<WalletHistoryView,WalletHistoryPresenter>(),WalletHistoryView{
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var mUserID:String
    private lateinit var mRs_Sign:String
    override fun getContentView(): Int {
        return R.layout.fragment_wallet_history
    }

    override fun getPresenterClass(): Class<WalletHistoryPresenter> {
        return WalletHistoryPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mUserID = sharedPrefManager.user.id.toString()
        mRs_Sign = getString(R.string.Rs)
            presenter?.hitGetWalletHistoryList(mUserID)
    }

    override fun getWalletHistoryListResponse(response: WalletListData) {
        if(response.status == 200)
        {
            wallet_history_total_amount.text = mRs_Sign+" "+response.walletAmount?.amount
            wallet_history_list_rc.adapter = WalletHistoryListAdapter(requireContext(),response)
        }
    }

}