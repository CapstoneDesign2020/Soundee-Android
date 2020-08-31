package com.soundee.soundee.data.vo

import com.google.gson.annotations.SerializedName

data class ChartDetails(
    @SerializedName("class")
    val soundClass: String,
    @SerializedName("date")
    val soundDate:String,
    val value : Int
)