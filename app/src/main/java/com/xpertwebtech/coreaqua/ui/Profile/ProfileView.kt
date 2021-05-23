package com.xpertwebtech.coreaqua.ui.Profile

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData

interface ProfileView:BaseView {

    fun getWalletBalance(response:WalletListData)
}