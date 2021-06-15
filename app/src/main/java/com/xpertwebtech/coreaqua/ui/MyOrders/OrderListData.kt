package com.xpertwebtech.coreaqua.ui.MyOrders
import com.google.gson.annotations.SerializedName


data class OrderListData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("user_order_listing")
    val userOrderListing: List<UserOrderListing>?
)

data class UserOrderListing(
    @SerializedName("address_id")
    val addressId: String?,
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("delivery_boy_id")
    val deliveryBoyId: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("order_id")
    val orderId: String?,
    @SerializedName("order_status")
    val orderStatus: String?,
    @SerializedName("payment_mode")
    val paymentMode: String?,
    @SerializedName("payment_status")
    val paymentStatus: String?,
    @SerializedName("payment_transaction_id")
    val paymentTransactionId: String?,
    @SerializedName("product_id")
    val productId: String?,
    @SerializedName("quantity")
    val quantity: String?,
    @SerializedName("status_name")
    val statusName: String?,
    @SerializedName("total_amount")
    val totalAmount: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("user_address")
    val userAddress: String?,
    @SerializedName("user_city")
    val userCity: String?,
    @SerializedName("user_email")
    val userEmail: String?,
    @SerializedName("user_id")
    val userId: String?,
    @SerializedName("user_name")
    val userName: String?,
    @SerializedName("user_phone")
    val userPhone: String?,
    @SerializedName("user_pincode")
    val userPincode: String?,
    @SerializedName("user_state")
    val userState: String?,
    @SerializedName("wallet_amount")
    val walletAmount: String?
)