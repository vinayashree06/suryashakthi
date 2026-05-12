package com.example.suryashakthi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class AnalyticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        val barChart = findViewById<BarChart>(R.id.barChart)

        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(1f, 50f))
        entries.add(BarEntry(2f, 80f))
        entries.add(BarEntry(3f, 65f))
        entries.add(BarEntry(4f, 90f))
        entries.add(BarEntry(5f, 70f))

        val dataSet = BarDataSet(entries, "Solar Energy")

        val data = BarData(dataSet)

        barChart.data = data

        barChart.animateY(1000)

        barChart.invalidate()
    }
}