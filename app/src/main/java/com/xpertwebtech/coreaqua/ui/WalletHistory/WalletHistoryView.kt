package com.xpertwebtech.coreaqua.ui.WalletHistory

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData

interface WalletHistoryView: BaseView {
    fun getWalletHistoryListResponse(response:WalletListData)
}