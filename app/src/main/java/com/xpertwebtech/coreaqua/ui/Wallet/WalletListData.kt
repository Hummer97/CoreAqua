package com.xpertwebtech.coreaqua.ui.Wallet
import com.google.gson.annotations.SerializedName


data class WalletListData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("user_wallet_history")
    val userWalletHistory: List<UserWalletHistory>?,
    @SerializedName("wallet_amount")
    val walletAmount: WalletAmount?
)

data class UserWalletHistory(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("payment_method")
    val paymentMethod: String?,
    @SerializedName("payment_request_id")
    val paymentRequestId: String?,
    @SerializedName("payment_status")
    val paymentStatus: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("used_refer_code")
    val usedReferCode: Any?,
    @SerializedName("user_id")
    val userId: String?,
    @SerializedName("user_refer_code")
    val userReferCode: String?
)

data class WalletAmount(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("user_id")
    val userId: String?
)