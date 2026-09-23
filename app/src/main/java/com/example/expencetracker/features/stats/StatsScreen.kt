package com.example.expencetracker.features.stats

import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.data.Entry
import com.example.expencetracker.R
import com.example.expencetracker.Utils
import com.example.expencetracker.viewmodel.StatsViewModel
import com.example.expencetracker.viewmodel.StatsViewModelFactor
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun StatsScreen(navController: NavController){
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.baseline_chevron_left_24
                    ),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.align(Alignment.CenterStart
                    )

                )


                Text(
                    text = "Statistics",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.dots_menu
                    ),
                    contentDescription = null,

                    modifier = Modifier.align(Alignment.CenterEnd),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Black)
                )
            }

        }
    ) {
        val viewModel = StatsViewModelFactor(navController.context).create(StatsViewModel::class.java)
        val dataState = viewModel.entries.collectAsState(emptyList())
        Column(modifier = Modifier.padding(it)) {
            val entries = viewModel.getEnteriesForChart(dataState.value)
            LineChart(entries = entries)
        }
    }

}

@Composable
fun LineChart(entries: List<Entry>) {
    val context = LocalContext.current

    AndroidView(
        factory = {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.stats_line_chart, null)
            view
        },
        modifier = Modifier.fillMaxWidth().height(250.dp)
    ) { view ->

        val lineChart = view.findViewById<LineChart>(R.id.lineChart)

        val dataset = LineDataSet(entries, "Expenses").apply {
            color = android.graphics.Color.parseColor("#FF2F7E79")
            valueTextColor = android.graphics.Color.BLACK
            lineWidth = 3f
            axisDependency = YAxis.AxisDependency.RIGHT
            setDrawFilled(true)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            valueTextSize = 12f
        }
        
        lineChart.xAxis.valueFormatter =
            object :com.github.mikephil.charting.formatter.ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return Utils.formatDataForChart(value.toLong())
                }
            }

        lineChart.data = LineData(dataset)
        lineChart.axisLeft.isEnabled = false
        lineChart.axisRight.setDrawGridLines(false)
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.xAxis.position= XAxis.XAxisPosition.BOTTOM
        lineChart.invalidate()
    }
}