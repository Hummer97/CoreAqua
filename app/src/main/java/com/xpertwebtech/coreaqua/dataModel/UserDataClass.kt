package com.xpertwebtech.coreaqua.dataModel
import com.google.gson.annotations.SerializedName


data class UserDataClass(
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: User?,
    @SerializedName("status")
    val status: Int?
)

data class User(
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
    @SerializedName("used_refer_code")
    val usedReferCode: Any?,
    @SerializedName("user_type")
    val userType: String?
)