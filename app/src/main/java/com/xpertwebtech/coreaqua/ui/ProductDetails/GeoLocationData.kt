package com.xpertwebtech.coreaqua.ui.ProductDetails

import com.google.gson.annotations.SerializedName

data class GeoLocationData(
    @SerializedName("accuracy")
    val accuracy: Int?,
    @SerializedName("location")
    val location: Location?
)

data class Location(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?
)