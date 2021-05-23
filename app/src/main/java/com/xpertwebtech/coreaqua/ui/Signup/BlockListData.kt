package com.xpertwebtech.coreaqua.ui.Signup
import com.google.gson.annotations.SerializedName


data class BlockListData(
    @SerializedName("block")
    val block: List<Block>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)

data class Block(
    @SerializedName("address")
    val address: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("sector")
    val sector: Any?,
    @SerializedName("updated_at")
    val updatedAt: String?
)