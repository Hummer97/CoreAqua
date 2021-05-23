package com.xpertwebtech.coreaqua.dataModel
import com.google.gson.annotations.SerializedName


data class UserDataClass(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("user")
    val user: User?
)

data class User(
    @SerializedName("address")
    val address: String?,
    @SerializedName("block")
    val block: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("refer_code")
    val referCode: Any?,
    @SerializedName("sector")
    val sector: String?,
    @SerializedName("used_refer_code")
    val usedReferCode: Any?,
    @SerializedName("user_type")
    val userType: String?
)