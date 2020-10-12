package com.example.utill.extension

import android.graphics.Color
import android.graphics.Color.rgb
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.ipos.saler.R
import com.ipos.saler.util.FormatNumberUtil
import java.util.*


fun initBarChartGroup(viewChart: BarChart, time: ArrayList<String>) {
    viewChart.description.isEnabled = false
    viewChart.axisRight.isEnabled = false
    viewChart.legend.isEnabled = false

//    viewChart.setViewPortOffsets(0f, 0f, 0f, 0f)
//    viewChart.setMaxVisibleValueCount(5)
//    viewChart.setVisibleXRangeMaximum(5f)

    viewChart.isDoubleTapToZoomEnabled = false
//    viewChart.setPinchZoom(true)
    viewChart.isDragEnabled = true
//    viewChart.setTouchEnabled(true)
//    viewChart.setScaleEnabled(true)

//    viewChart.setDrawGridBackground(false)
//    viewChart.setDrawBarShadow(false)
//    viewChart.setDrawValueAboveBar(false)
//    viewChart.isHighlightFullBarEnabled = false
    viewChart.setFitBars(true)

    val y: YAxis = viewChart.axisLeft
    y.labelCount = 6
    y.setDrawAxisLine(false)
    y.setDrawGridLines(true)
//    y.textColor = getColor(R.color.gray80)
    y.granularity = 1f
    y.isGranularityEnabled = true
    y.mAxisMinimum = 0f
    y.textSize = 11f
    y.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            if (value.toLong() >= 0)
                return FormatNumberUtil.formatNumberByInteger(value.toLong())
            else
                return ""
        }
    }

    val xl = viewChart.xAxis
    xl.position = XAxis.XAxisPosition.BOTTOM
    xl.labelCount = 12
    xl.setDrawAxisLine(false)
    xl.setDrawGridLines(false)
    xl.setCenterAxisLabels(true)
//    xl.isGranularityEnabled = true
    xl.granularity = 1f
    xl.textSize = 11f
    xl.axisMinimum = 0f
//    xl.axisMaximum = 0.5f
//    xl.axisMaximum = (0f + viewChart.barData.getGroupWidth(0.10f, 0f) * 12)
//    xl.textColor = resources.getColor(R.color.gray80)
//    xl.labelRotationAngle = 315f
//    xl.spaceMax = 0.5f
//    xl.spaceMin = 0.5f
    xl.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
//            val index = Math.floor(value.toDouble()).toInt()
            val index = value.toInt()
            return if (index >= time.size || index < 0) "" else time[index]
        }
    }
    xl.isEnabled = true
//    viewChart.animateY(3000, Easing.EaseInOutQuad)
}

fun initHorizolChart(chart: HorizontalBarChart, time: ArrayList<String>? = null) {
    // chart.setHighlightEnabled(false);
    chart.setDrawBarShadow(false)

    chart.setDrawValueAboveBar(true)

    chart.description.isEnabled = false
    chart.isDoubleTapToZoomEnabled = false

    chart.setMaxVisibleValueCount(60)

    chart.setPinchZoom(false)

    chart.setDrawGridBackground(false)
    val xl: XAxis = chart.xAxis
    xl.position = XAxisPosition.BOTTOM
    xl.labelCount = 5
    xl.setDrawAxisLine(true)
    xl.setDrawGridLines(false)
    xl.granularity = 1f
//    xl.textSize = 10f
    xl.isEnabled = true
    time?.run {
        xl.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String? {
//            val index = Math.floor(value.toDouble()).toInt()
                var index = value.toInt()
//                Timber.e("indext: " + index)
                return if (index >= time.size || index < 0) "" else time[index]
            }
        }
    }

    val yl: YAxis = chart.axisLeft
    xl.labelCount = 4
    yl.setDrawAxisLine(true)
    yl.setDrawGridLines(true)
    yl.axisMinimum = 0f // this replaces setStartAtZero(true)
    yl.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            return if (value.toLong() >= 0)
                FormatNumberUtil.formatNumberByInteger(value.toLong())
            else
                ""
        }
    }
    val yr: YAxis = chart.axisRight
    yr.setDrawAxisLine(true)
    yr.setDrawGridLines(false)
    yr.isEnabled = false
    yr.axisMinimum = 0f // this replaces setStartAtZero(true)

    chart.setFitBars(true)
//    chart.animateY(2500)

    val l: Legend = chart.legend
    l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
    l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
    l.orientation = Legend.LegendOrientation.HORIZONTAL
    l.setDrawInside(false)
    l.formSize = 8f
    l.xEntrySpace = 4f
    l.isEnabled = false
}

fun initHorizolChartPartnerCommission(chart: HorizontalBarChart, time: ArrayList<String>? = null) {
    // chart.setHighlightEnabled(false);
    chart.setDrawBarShadow(false)

    chart.setDrawValueAboveBar(true)

    chart.description.isEnabled = false
    chart.isDoubleTapToZoomEnabled = false

    chart.setMaxVisibleValueCount(60)

    chart.setPinchZoom(false)

    chart.setDrawGridBackground(false)

    val xl: XAxis = chart.xAxis
    xl.position = XAxisPosition.BOTTOM
    xl.labelCount = 5
    xl.setDrawAxisLine(true)
    xl.setDrawGridLines(false)
    xl.granularity = 1f
//    xl.textSize = 10f
    xl.isEnabled = true
    time?.run {
        xl.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String? {
//            val index = Math.floor(value.toDouble()).toInt()
                var index = value.toInt()
//                Timber.e("indext: " + index)
                return if (index >= time.size || index < 0) "" else time[index]
            }
        }
    }

    val yl: YAxis = chart.axisLeft
    xl.labelCount = 4
    yl.setDrawAxisLine(true)
    yl.setDrawGridLines(true)
    yl.axisMinimum = 0f // this replaces setStartAtZero(true)
    yl.isEnabled = false
    yl.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            if (value.toLong() >= 0)
                return FormatNumberUtil.formatNumberByInteger(value.toLong())
            else
                return ""
        }
    }
//        yl.setInverted(true);

    //        yl.setInverted(true);
    val yr: YAxis = chart.axisRight
    yr.setDrawAxisLine(true)
    yr.setDrawGridLines(false)
    yr.isEnabled = true
    yr.axisMinimum = 0f // this replaces setStartAtZero(true)
    yr.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            if (value.toLong() >= 0)
                return FormatNumberUtil.formatNumberByInteger(value.toLong())
            else
                return ""
        }
    }

//        yr.setInverted(true);

    //        yr.setInverted(true);
    chart.setFitBars(true)
//    chart.animateY(2500)

    // setting data

    // setting data

    val l: Legend = chart.legend
    l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
    l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
    l.orientation = Legend.LegendOrientation.HORIZONTAL
    l.setDrawInside(false)
    l.formSize = 8f
    l.xEntrySpace = 4f
    l.isEnabled = false
}


fun initLineChartGroup(viewChart: LineChart, time: ArrayList<String>) {
    viewChart.setBackgroundColor(Color.WHITE)
    viewChart.axisRight.isEnabled = false
    viewChart.legend.isEnabled = false
    viewChart.description.isEnabled = false
    viewChart.setTouchEnabled(true)
    viewChart.isDragEnabled = true
    viewChart.setScaleEnabled(true)
    viewChart.isDoubleTapToZoomEnabled = false
    viewChart.setPinchZoom(true)

    viewChart.setDrawGridBackground(false)
//    viewChart.renderer = ImageLineChartRenderer(viewChart, viewChart.animator, viewChart.viewPortHandler)

    val y: YAxis = viewChart.axisLeft
    y.labelCount = 6
    y.setDrawAxisLine(false)
    y.setDrawGridLines(true)
//    y.textColor = getColor(R.color.gray80)
    y.granularity = 1f
    y.isGranularityEnabled = true
    y.mAxisMinimum = 0f
    y.textSize = 11f
    y.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            if (value.toLong() >= 0)
                return FormatNumberUtil.formatNumberByInteger(value.toLong())
            else
                return ""
        }
    }

    val xl = viewChart.xAxis
    xl.position = XAxis.XAxisPosition.BOTTOM
    xl.labelCount = 4
    xl.setDrawAxisLine(false)
    xl.setDrawGridLines(false)
    xl.setCenterAxisLabels(false)
//    xl.isGranularityEnabled = true
    xl.granularity = 1f
    xl.textSize = 11f
    xl.axisMinimum = 0f
//    xl.axisMaximum = 0.5f
//    xl.axisMaximum = (0f + viewChart.barData.getGroupWidth(0.10f, 0f) * 12)
//    xl.textColor = resources.getColor(R.color.gray80)
//    xl.labelRotationAngle = 315f
//    xl.spaceMax = 0.5f
//    xl.spaceMin = 0.5f
    xl.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            val index = value.toInt()
            return if (index >= time.size || index < 0) "" else time[index]
        }
    }
    xl.isEnabled = true
//    viewChart.animateY(3000, Easing.EaseInOutQuad)
}

val colors = arrayOf(
    "#0560A6",
    "#FBA700",
    "#18BFAE",
    "#F95E25",
    "#80BA3F",
    "#AAAAAA"
)

val colorsFormratInt = intArrayOf(
    rgb("#0560A6"),
    rgb("#FBA700"),
    rgb("#18BFAE"),
    rgb("#F95E25"),
    rgb("#80BA3F"),
    rgb("#AAAAAA")
)

val colorsHightLight = arrayOf(
    R.color.hightlight_chart1,
    R.color.hightlight_chart2,
    R.color.hightlight_chart3,
    R.color.hightlight_chart4,
    R.color.hightlight_chart5,
    R.color.hightlight_chart6,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7,
    R.color.hightlight_chart7
)

fun rgb(hex: String): Int {
    val color = hex.replace("#", "").toLong(16).toInt()
    val r = color shr 16 and 0xFF
    val g = color shr 8 and 0xFF
    val b = color shr 0 and 0xFF
    return rgb(r, g, b)
}

class XAxisValueFormatter(private val values: Array<String>) :
    IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        // "value" represents the position of the label on the axis (x or y)
        return values[value.toInt()]
    }

}
