package com.soundee.soundee.chart

import android.content.Context
import android.graphics.Canvas
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.soundee.soundee.R
import kotlinx.android.synthetic.main.markerview_monthly.view.*


class MonthlyMarkerView(context: Context?, layoutResource:Int= R.layout.markerview_monthly) : MarkerView(context, layoutResource) {

    //entry를 content의 텍스트에 지정
    override fun refreshContent(e: Entry?, highlight: Highlight?) {

        txt_marker_month_num.text=e?.y?.toInt().toString()

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF? {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }

}