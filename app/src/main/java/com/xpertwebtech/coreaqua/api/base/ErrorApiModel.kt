package com.bb.bigbrother.api.base

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorApiModel(
    @SerializedName("ErrorDetails")
    val errorDetails: String, // Email already exists or Something went wrong. Please try again
    @SerializedName("ErrorMessage")
    val errorMessage: String // Error!
)