package com.xpertwebtech.coreaqua.ui.Wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_wallet.*

class WalletFragment : BaseFragment<WalletView,WalletPresenter>(),WalletView {
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var mUserID:String
    override fun getContentView(): Int {
        return R.layout.fragment_wallet
    }

    override fun getPresenterClass(): Class<WalletPresenter> {
        return WalletPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mUserID = sharedPrefManager.user.id.toString()
        presenter?.hitGetWalletHistoryList(mUserID)
       wallet_paylist_rc.adapter= PayAdapter(requireContext())

    }

    override fun getWalletHistoryListResponse(response: WalletListData) {
        wallet_available_balance.text = "Rs. "+response.walletAmount?.amount
    }

}