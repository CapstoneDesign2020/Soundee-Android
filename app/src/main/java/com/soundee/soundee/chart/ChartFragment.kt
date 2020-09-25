package com.soundee.soundee.chart

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.soundee.soundee.R
import com.soundee.soundee.data.RepositoryImpl
import com.soundee.soundee.data.vo.ChartDetails
import com.soundee.soundee.data.vo.MonthlyLineChartDetails
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.util.RoundedBarChartRenderer
import kotlinx.android.synthetic.main.fragment_chart.*

public class ChartFragment : Fragment(R.layout.fragment_chart) {
    private val listData = ArrayList<BarEntry>()
    val listRVData = ArrayList<ChartDetails>()
    val listLineData= ArrayList<Entry>()
    private val dailyChartDetailsAdapter = ChartDetailsRecyclerViewAdapter()
    lateinit var monthlyLineData :List<MonthlyLineChartDetails>
    lateinit var marker: MonthlyMarkerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getDailyPieChartListData()
        getWeeklyBarChartData()
        getMonthlyLineChartData()


        drawDailyChartDetails()
        drawWeeklyChartDetails()
        //drawMonthlyChartDetails()

        initBarChart()
        drawBarChart(listData)
        drawLineChart(listLineData)

    }

    private fun getDailyPieChartListData() {
        val listPieData = ArrayList<PieEntry>()
        Log.e("뭐야뭐야", listPieData.toString())
        val pieDataSet = PieDataSet(listPieData, "")
        val listPieColor = ArrayList<Int>()

        if (SoundeeUserController.getToken(context) != "") {
            RepositoryImpl.getDailyPieChart(SoundeeUserController.getToken(context)!!, {
                Log.e("연결 된거 맞나요", it.toString())
                if (it.success) {
                    val list = it.data
                    Log.e("데이터는 잘 받아오나", list.toString())
                    for (i in list) {
                        Log.e("i는 뭘까", i.toString())
                        i.let {
                            if (it.value > 0) {
                                listRVData.add(it)
                                listPieData.add(PieEntry(it.value.toFloat() * 10, it.soundClass))
                                when (it.soundClass) {
                                    "water" -> {
                                        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorWaterBlue))
                                    }
                                    "motor" -> {
                                        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorMotorGreen))
                                    }
                                    "baby" -> {
                                        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorBabyCryingOrange))
                                    }
                                    "drop" -> {
                                        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorDropObjRed))
                                    }
                                    "glass" -> {
                                        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorBrokenGlassPurple))
                                    }
                                    "siren" -> {
                                        listPieColor.add(ContextCompat.getColor(context!!, R.color.colorSirenDeepPurple)
                                        )
                                    }
                                }
                            }
                            Log.e("데이터?", listPieData.toString())
                        }
                    }

                    pieDataSet.colors = listPieColor
                    pieDataSet.setDrawValues(false)
                    drawPieChart(pieDataSet)
                    drawDailyChartDetails()
                }
            }, {})
        }
    }
    private fun drawDailyChartDetails() {
        rv_daily_chart_details.adapter = dailyChartDetailsAdapter
        dailyChartDetailsAdapter.data = listRVData
        rv_daily_chart_details.layoutManager = GridLayoutManager(context, 2)
        dailyChartDetailsAdapter.notifyDataSetChanged()
    }
    private fun drawWeeklyChartDetails() {
        rv_weekly_chart_details.adapter = dailyChartDetailsAdapter
        dailyChartDetailsAdapter.data = listRVData
        rv_weekly_chart_details.layoutManager = GridLayoutManager(context, 2)
        dailyChartDetailsAdapter.notifyDataSetChanged()
    }

    private fun drawMonthlyChartDetails() {
        rv_monthly_chart_details.adapter = marker.monthlyChartDetailsAdapter
        rv_monthly_chart_details.layoutManager = GridLayoutManager(context, 2)
        marker.monthlyChartDetailsAdapter.notifyDataSetChanged()
    }


    private fun initBarChart() {
        val xAxis = chart_bar_weekly.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.axisLineWidth = 3f



        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)

        val xLabelIngredients =
            arrayOf("일", "월", "화", "수", "목", "금", "토")


        val formatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return xLabelIngredients[value.toInt()]
            }
        }
        xAxis.valueFormatter = formatter
        xAxis.mLabelWidth = 10
        xAxis.mLabelHeight = 10
        xAxis.labelCount=7
        xAxis.mAxisMaximum=7f


        val rightYAxis = chart_bar_weekly.axisRight
        rightYAxis.isEnabled = false

        val leftYAxis = chart_bar_weekly.axisLeft
        leftYAxis.axisMaximum = 20f
        leftYAxis.axisMinimum = 0f
        leftYAxis.setDrawGridLines(false) // false 해줘야 뒤에 모눈종이 같은게 없어져요
        leftYAxis.setDrawAxisLine(false)
        leftYAxis.setDrawLabels(false)

        val roundedBarChartRenderer = RoundedBarChartRenderer(
            chart_bar_weekly,
            chart_bar_weekly.animator,
            chart_bar_weekly.viewPortHandler
        )
        //val paint = roundedBarChartRenderer.paintRender

        chart_bar_weekly.renderer = roundedBarChartRenderer
        chart_bar_weekly.legend.isEnabled = false
        chart_bar_weekly.description.isEnabled = false


    }


    private fun dummyChartListData() {
        listData.add(BarEntry(0f, 100f))
        listData.add(BarEntry(1f, 20f))
        listData.add(BarEntry(2f, 60f))
        listData.add(BarEntry(3f, 80f))
        listData.add(BarEntry(4f, 120f))
        listData.add(BarEntry(5f, 40f))
        listData.add(BarEntry(6f, 55f))
    }

    private fun getWeeklyBarChartData(){
        if(SoundeeUserController.getToken(context)!=""){
            RepositoryImpl.getWeeklyBarChart(SoundeeUserController.getToken(context)!!,{
                if(it.status==200){
                    Log.e("주간데이터",it.toString())
                    val weeklyBarData = it.data
                    Log.e("주간데이터 그래프 정보",weeklyBarData.toString())
                    for( i in weeklyBarData){
                        Log.e("주간데이터 개별 그래프 ",i.toString())
                        var lastday :Float= 0f
                        when(i.day){
                            "sun"->{
                                listData.add(BarEntry(0f,i.soundSum.toFloat()))
                            }
                            "mon"->{
                                lastday=1f
                                listData.add(BarEntry(1f,i.soundSum.toFloat()))
                            }
                            "tue"->{
                                lastday=2f
                                listData.add(BarEntry(2f,i.soundSum.toFloat()))
                            }
                            "wed"->{
                                lastday=3f
                                listData.add(BarEntry(3f,i.soundSum.toFloat()))
                            }
                            "thu"->{
                                lastday=4f
                                listData.add(BarEntry(4f,i.soundSum.toFloat()))
                            }
                            "fri"->{
                                lastday=5f
                                listData.add(BarEntry(5f,i.soundSum.toFloat()))
                            }
                            "sat"->{
                                lastday=6f
                                listData.add(BarEntry(6f,i.soundSum.toFloat()))
                            }
                        }
                        //아직 지나지 않은 요일
                        while (lastday<6f){
                            lastday++
                            listData.add(BarEntry(lastday.toFloat(),0f))
                        }
                    }
                    drawBarChart(listData)
                }
            },{})
        }

    }

    private fun drawBarChart(listData: ArrayList<BarEntry>) {
        val dataSet = BarDataSet(listData, "")

        val listColor = ArrayList<Int>()

        listData.forEach {

               // listColor.add(ContextCompat.getColor(context!!, R.color.colorOrange))

                listColor.add(ContextCompat.getColor(context!!, R.color.colorPointGreen))
        }
        dataSet.colors = listColor
        dataSet.setDrawValues(false)


        val barData = BarData(dataSet)
        barData.barWidth = 0.3f
        chart_bar_weekly.data = barData

        chart_bar_weekly.invalidate()
    }

    private fun drawPieChart(pieDataSet: PieDataSet) {

        val pieData = PieData(pieDataSet)
        chart_pie_daily.data = pieData
        chart_pie_daily.description.isEnabled = false

        val pieLegend = chart_pie_daily.legend
        pieLegend.form = Legend.LegendForm.CIRCLE
        pieLegend.direction = Legend.LegendDirection.RIGHT_TO_LEFT
        pieLegend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        pieLegend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        pieLegend.orientation = Legend.LegendOrientation.VERTICAL
        pieLegend.maxSizePercent = 0.2f


        chart_pie_daily.setDrawSliceText(false)
        chart_pie_daily.animateXY(1000, 1000)
        chart_pie_daily.invalidate()
    }


    private fun drawLineChart(listData: ArrayList<Entry>) {
        Log.e("넘어왔나",listData.toString())
        val lineDataSet = LineDataSet(listData, "")
        lineDataSet.color = ContextCompat.getColor(context!!, R.color.colorChartMint)
        lineDataSet.setDrawCircles(false)
        //lineDataSet.circleRadius = 5f
        lineDataSet.setDrawHorizontalHighlightIndicator(false)
        lineDataSet.highLightColor=ContextCompat.getColor(context!!,R.color.colorPointGreen)
        lineDataSet.highlightLineWidth=2f
        lineDataSet.setDrawValues(false)
        lineDataSet.lineWidth = 5f
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER


        val xAxis = chart_line_monthly.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.labelCount = 12
        xAxis.axisLineWidth = 3f
        xAxis.axisMaximum=12f
        xAxis.gridColor = R.color.colorChartGray
        xAxis.granularity = 1f
        xAxis.enableGridDashedLine(20f, 10f, 0f)
        xAxis.gridLineWidth = 0.4f
        xAxis.setDrawAxisLine(false)


        val rightYAxis = chart_line_monthly.axisRight
        rightYAxis.isEnabled = false

        val leftYAxis = chart_line_monthly.axisLeft
        leftYAxis.axisMaximum = 100f
        leftYAxis.axisMinimum = 0f
        leftYAxis.setDrawGridLines(false) // false 해줘야 뒤에 모눈종이 같은게 없어져요
        leftYAxis.setDrawAxisLine(false)
        leftYAxis.setDrawLabels(false)

        marker= MonthlyMarkerView(context)
        chart_line_monthly.marker=marker
        drawMonthlyChartDetails()


        chart_line_monthly.legend.isEnabled = false
        chart_line_monthly.description.isEnabled = false
        chart_line_monthly.data = LineData(lineDataSet)

    }

    private fun getMonthlyLineChartData(): ArrayList<Entry>{

        if(SoundeeUserController.getToken(context)!=""){
            RepositoryImpl.getMonthlyLineChart(SoundeeUserController.getToken(context)!!,{
                if(it.status==200){
                    monthlyLineData = it.data
                    for (i in monthlyLineData){
                        listLineData.add(Entry(i.month.toFloat(), i.soundSum.toFloat()))
                    }
                    var lastMonth=monthlyLineData.lastIndex
                    while (lastMonth<12){
                        lastMonth++
                       // listLineData.add(Entry(lastMonth.toFloat())
                    }
                    Log.e("line data",listLineData.toString())
                    drawLineChart(listLineData)
                    marker.monthlyLineData.addAll(monthlyLineData)
                }

            },{})
        }

        return listLineData
    }


}

