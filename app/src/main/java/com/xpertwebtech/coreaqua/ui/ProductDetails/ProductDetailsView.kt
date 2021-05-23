package com.xpertwebtech.coreaqua.ui.ProductDetails

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData

interface ProductDetailsView:BaseView {

    fun getWalletBalanceResponse(response: WalletListData)
    fun getOrderSelectedPlanResponse(response: ProductDetailsResponseData)
    fun getProductListResponse(response: ProductListData)
}
