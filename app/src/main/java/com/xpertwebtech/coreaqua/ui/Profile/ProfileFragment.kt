package com.xpertwebtech.coreaqua.ui.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<ProfileView,ProfilePresenter>(),ProfileView{
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var mUserName:String
    private lateinit var mUserID:String
    override fun getContentView(): Int {
        return R.layout.fragment_profile
    }

    override fun getPresenterClass(): Class<ProfilePresenter> {
        return ProfilePresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager  = SharedPrefManager.getInstance(requireContext())
        mUserName = sharedPrefManager.user.name.toString()
        mUserID = sharedPrefManager.user.id.toString()
        profile_user_name.text = mUserName
        presenter?.hitWalletBalanceResponseApi(mUserID)
        profile_wallet.setOnClickListener {
            Navigation.findNavController(profile_wallet).navigate(R.id.action_nav_profile_to_walletHistoryFragment3)
        }
        profile_wallet_history_btn.setOnClickListener {
            Navigation.findNavController(profile_wallet_history_btn).navigate(R.id.action_nav_profile_to_walletHistoryFragment3)
        }
        profile_profile_add_balance_btn.setOnClickListener {
            Navigation.findNavController(profile_wallet).navigate(R.id.action_nav_profile_to_walletHistoryFragment3)
        }
        profile_sign_out_btn.setOnClickListener {
            sharedPrefManager.logout()
        }
        profile_edit_profile_btn.setOnClickListener {
            Navigation.findNavController(profile_wallet).navigate(R.id.action_nav_profile_to_profileDetailsFragment)
        }
        profile_my_order.setOnClickListener {
            Navigation.findNavController(profile_my_order).navigate(R.id.action_nav_profile_to_orderListFragment)
        }

    }

    override fun getWalletBalance(response: WalletListData) {
        if (response.status == 200)
        {
            profile_wallet_balance.text = "Rs. "+response.walletAmount?.amount
        }
    }

}