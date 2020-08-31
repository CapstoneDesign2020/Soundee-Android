package com.soundee.soundee.data.vo

import com.google.gson.annotations.SerializedName

data class WeeklyBarChartResponse(
    val status:Int,
    val success:Boolean,
    val message:String,
    val data : List<WeeklyBarChartDetails>
)

data class WeeklyBarChartDetails(
    val day: String,
    val soundSum:String,
    val details: List<ChartDetails>
)