package com.bb.bigbrother.api.base

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseApiModel(
    @SerializedName("Message")
    val message: String // You have been signed up successfully!
)