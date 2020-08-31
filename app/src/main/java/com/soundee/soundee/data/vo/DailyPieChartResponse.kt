package com.soundee.soundee.data.vo

import com.google.gson.annotations.SerializedName

data class DailyPieChartResponse(
    val status:Int,
    val success:Boolean,
    val message:String,
    val data : List<ChartDetails>
)
