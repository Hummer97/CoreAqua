package com.xpertwebtech.coreaqua.ui.PaykunPayment

import com.xpertwebtech.coreaqua.base.BaseView

interface PaymentView:BaseView {
    fun getBookedOrderResponse(response: BookedOrderResponse)
}