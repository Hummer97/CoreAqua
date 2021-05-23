package com.xpertwebtech.coreaqua.ui.DashBoard
import com.google.gson.annotations.SerializedName


data class ProductListData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("product")
    val product: List<Product>?,
    @SerializedName("status")
    val status: Int?
)

data class Product(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("quantity")
    val quantity: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?
)