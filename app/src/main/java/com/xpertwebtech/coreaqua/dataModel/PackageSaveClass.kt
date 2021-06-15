package com.xpertwebtech.coreaqua.dataModel

import com.google.gson.annotations.SerializedName

data class PackageSaveClass(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("user_sms")
    val userSms: UserSms?
)

data class UserSms(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("message_qty")
    val messageQty: String?,
    @SerializedName("package_name")
    val packageName: String?,
    @SerializedName("package_price")
    val packagePrice: String?,
    @SerializedName("package_validity")
    val packageValidity: String?,
    @SerializedName("payment_status")
    val paymentStatus: String?,
    @SerializedName("sender_id")
    val senderId: String?,
    @SerializedName("transaction_id")
    val transactionId: String?,
    @SerializedName("user_id")
    val userId: String?
)