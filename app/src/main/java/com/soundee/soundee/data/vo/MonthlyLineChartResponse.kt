package com.soundee.soundee.data.vo

data class MonthlyLineChartResponse (
    val status: String,
    val success : Boolean,
    val message: String,
    val data : List<MonthlyLineChartDetails>
)
data class MonthlyLineChartDetails(
    val month :Int,
    val soundSum : Int,
    val details:List<ChartDetails>
)