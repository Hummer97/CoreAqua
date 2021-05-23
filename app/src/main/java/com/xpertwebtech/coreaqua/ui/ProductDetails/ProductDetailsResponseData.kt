package com.xpertwebtech.coreaqua.ui.ProductDetails
import com.google.gson.annotations.SerializedName


data class ProductDetailsResponseData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("user_order")
    val userOrder: UserOrder?
)

data class UserOrder(
    @SerializedName("block")
    val block: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("day_type")
    val dayType: String?,
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product_id")
    val productId: String?,
    @SerializedName("quantity")
    val quantity: String?,
    @SerializedName("sector")
    val sector: String?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("user_id")
    val userId: String?
)