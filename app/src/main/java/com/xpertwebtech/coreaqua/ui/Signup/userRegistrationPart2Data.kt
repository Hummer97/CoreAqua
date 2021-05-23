package com.xpertwebtech.coreaqua.ui.Signup
import com.google.gson.annotations.SerializedName


data class userRegistrationPart2Data(
        @SerializedName("message")
    val message: String?,
        @SerializedName("sign_up_respo")
    val signUpRespo: SignUpPart2Response?,
        @SerializedName("status")
    val status: Int?
)

data class SignUpPart2Response(
    @SerializedName("address")
    val address: String?,
    @SerializedName("block")
    val block: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("refer_code")
    val referCode: String?,
    @SerializedName("sector")
    val sector: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("used_refer_code")
    val usedReferCode: Any?,
    @SerializedName("user_type")
    val userType: String?
)