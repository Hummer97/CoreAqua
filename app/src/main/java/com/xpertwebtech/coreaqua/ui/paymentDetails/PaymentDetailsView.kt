package com.xpertwebtech.coreaqua.ui.paymentDetails

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.ui.PaykunPayment.BookedOrderResponse
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData

interface PaymentDetailsView:BaseView {
    fun getUserAddressResponse(response: SingleUserAddressData)
    fun getUserOrderCountResponse(response:OrderCountData)
    fun getOfflineBookedOrderResponse(response: BookedOrderResponse)
    fun getWalletAmountResponse(response: WalletListData)

}
