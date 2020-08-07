package com.soundee.soundee.chart

import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.soundee.soundee.R
import kotlinx.android.synthetic.main.fragment_chart.*

class ChartFragment : Fragment(R.layout.fragment_chart){
    private val listData = ArrayList<BarEntry>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initBarChart()
        dummyChartListData()
        drawBarChart(listData)

        drawPieChart()
        drawLineChart()
    }

    private fun initBarChart(){
        val xAxis = chart_bar_weekly.xAxis
        xAxis.position=XAxis.XAxisPosition.BOTTOM
        xAxis.granularity=1f
        //xAxis.labelCount=7
        xAxis.axisLineWidth=3f


        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)

        val xLabelIngredients =
            arrayOf("일","월","화","수","목","금","토")

        val formatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {

                return xLabelIngredients[value.toInt()]

            }
        }
        xAxis.valueFormatter=formatter
        xAxis.mLabelWidth=10
        xAxis.mLabelHeight=10


        val rightYAxis = chart_bar_weekly.axisRight
        rightYAxis.isEnabled = false

        val leftYAxis = chart_bar_weekly.axisLeft
        leftYAxis.axisMaximum = 120f
        leftYAxis.axisMinimum = 0f
        leftYAxis.setDrawGridLines(false) // false 해줘야 뒤에 모눈종이 같은게 없어져요
        leftYAxis.setDrawAxisLine(false)
        leftYAxis.setDrawLabels(false)

        chart_bar_weekly.legend.isEnabled=false
        chart_bar_weekly.description.isEnabled=false
    }
    private fun dummyChartListData() {
        listData.add(BarEntry(0f, 130f))
        listData.add(BarEntry(1f, 20f))
        listData.add(BarEntry(2f, 60f))
        listData.add(BarEntry(3f, 80f))
        listData.add(BarEntry(4f, 120f))
        listData.add(BarEntry(5f, 40f))
        listData.add(BarEntry(6f, 55f))

    }
    private fun drawBarChart(listData:ArrayList<BarEntry>){
        val dataSet=BarDataSet(listData,"")

        val listColor= ArrayList<Int>()

        listData.forEach{
            if (it.y > 100.0f || it.y < 30.0f)
                listColor.add(ContextCompat.getColor(context!!, R.color.colorOrange))
            else
                listColor.add(ContextCompat.getColor(context!!, R.color.colorPointGreen))
        }
        dataSet.colors=listColor
        dataSet.setDrawValues(false)

        val barData=BarData(dataSet)
        barData.barWidth=0.3f
        chart_bar_weekly.data= barData

        chart_bar_weekly.invalidate()
    }

    private fun drawPieChart(){
        val pieDataSet=PieDataSet(dummyPieChartListData(),"")
        val listPieColor= ArrayList<Int>()
        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorOrange))
        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorRed))
        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorPointGreen))
        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorPurple))

        pieDataSet.colors=listPieColor
        val pieData=PieData(pieDataSet)
        chart_pie_daily.data=pieData
        chart_pie_daily.animateXY(1000,1000)
        chart_pie_daily.invalidate()
    }
    private fun dummyPieChartListData()  : ArrayList<PieEntry>{
        val listPieData=ArrayList<PieEntry>()
        listPieData.add(PieEntry(60f, "물건 떨어지는 소리"))
        listPieData.add(PieEntry(20f, "물 소리"))
        listPieData.add(PieEntry(30f, "아기 우는 소리"))
        listPieData.add(PieEntry(40f, "모터 소리"))


        return listPieData

    }
    private fun drawLineChart(){
        val lineDataSet=LineDataSet(dummyLineChartListData(),"")
        lineDataSet.color=ContextCompat.getColor(context!!, R.color.colorPointGreen)

        val xAxis = chart_line_monthly.xAxis
        xAxis.position=XAxis.XAxisPosition.BOTTOM
        xAxis.granularity=1f
        //xAxis.labelCount=7
        xAxis.axisLineWidth=3f
        xAxis.axisLineWidth=0f

        val rightYAxis = chart_line_monthly.axisRight
        rightYAxis.isEnabled = false

        val leftYAxis = chart_line_monthly.axisLeft
        leftYAxis.axisMaximum = 10f
        leftYAxis.axisMinimum = 0f
        leftYAxis.setDrawGridLines(false) // false 해줘야 뒤에 모눈종이 같은게 없어져요
        leftYAxis.setDrawAxisLine(false)
        leftYAxis.setDrawLabels(false)

        chart_line_monthly.legend.isEnabled=false
        chart_line_monthly.description.isEnabled=false
        chart_line_monthly.data=LineData(lineDataSet)
    }
    private fun dummyLineChartListData()  : ArrayList<Entry>{
        val listLineData=ArrayList<Entry>()

            for (i in 0..20) {
            var a = (Math.random() * 10).toFloat()
            listLineData.add(Entry(i.toFloat(), a))
        }

        return listLineData

    }

}
