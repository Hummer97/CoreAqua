package com.xpertwebtech.coreaqua.ui.AddressList
import com.google.gson.annotations.SerializedName


data class AddressDeleteResponseData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)