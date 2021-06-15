package com.xpertwebtech.coreaqua.ui.MyOrders

import com.xpertwebtech.coreaqua.base.BaseView

interface OrderListView:BaseView {
    fun getUserOrderListResponse(response: OrderListData)

}
