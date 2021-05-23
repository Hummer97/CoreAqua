package com.xpertwebtech.coreaqua.ui.DashBoard

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData

interface DeshboardView:BaseView {

    fun getProductListResponse(response:ProductListData)

}