package com.soundee.soundee.chart

import android.content.Context
import android.util.Log
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.soundee.soundee.R
import com.soundee.soundee.data.vo.ChartDetails
import com.soundee.soundee.data.vo.MonthlyLineChartDetails
import kotlinx.android.synthetic.main.markerview_monthly.view.*



class MonthlyChartMarker(context: Context?, layoutResource:Int= R.layout.markerview_monthly) : MarkerView(context, layoutResource) {

    var month=0
    val monthlyChartDetailsAdapter = ChartDetailsRecyclerViewAdapter()
    val monthlyLineData =ArrayList<MonthlyLineChartDetails>()


    //entry를 content의 텍스트에 지정
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        val entry= e?.x?.toInt()
        month=getMonth(entry)
        notifyDataMonthlyChartDetails(month-1)
        txt_marker_month_num.text=e?.y?.toInt().toString()
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF? {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }

    fun getMonth(x:Int?):Int{
        return x?:0
    }
    fun notifyDataMonthlyChartDetails(month:Int){
        val monthDetailsSound=ArrayList<ChartDetails>()
        Log.e("먼슬리데이터",monthlyLineData.toString())
        monthDetailsSound.addAll(0,monthlyLineData[month].details)
        monthlyChartDetailsAdapter.data = monthDetailsSound
        monthlyChartDetailsAdapter.notifyDataSetChanged()
    }

}


