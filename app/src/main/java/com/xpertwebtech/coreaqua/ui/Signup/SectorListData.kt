package com.xpertwebtech.coreaqua.ui.Signup
import com.google.gson.annotations.SerializedName


data class SectorListData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("sector")
    val sector: List<Sector>?,
    @SerializedName("status")
    val status: Int?
)

data class Sector(
    @SerializedName("address")
    val address: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("sector")
    val sector: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)