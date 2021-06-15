package com.xpertwebtech.coreaqua.ui.MyOrderDetails
import com.google.gson.annotations.SerializedName


data class OrderStatusListingData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("order_status")
    val orderStatus: List<OrderStatu>?,
    @SerializedName("status")
    val status: Int?
)

data class OrderStatu(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("status_color")
    val statusColor: String?,
    @SerializedName("status_name")
    val statusName: String?,
    @SerializedName("status_value")
    val statusValue: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?
)