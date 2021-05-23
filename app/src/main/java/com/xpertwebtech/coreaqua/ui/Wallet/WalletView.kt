package com.xpertwebtech.coreaqua.ui.Wallet

import com.xpertwebtech.coreaqua.base.BaseView

interface WalletView:BaseView {
    fun getWalletHistoryListResponse(response:WalletListData)
}
