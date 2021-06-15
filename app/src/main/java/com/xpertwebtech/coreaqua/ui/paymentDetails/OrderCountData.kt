package com.xpertwebtech.coreaqua.ui.paymentDetails
import com.google.gson.annotations.SerializedName


data class OrderCountData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("total_orders")
    val totalOrders: Int?
)