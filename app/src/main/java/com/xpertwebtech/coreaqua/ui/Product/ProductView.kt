package com.xpertwebtech.coreaqua.ui.Product

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData

interface ProductView:BaseView {
    fun getProductListResponse(response: ProductListData)
}