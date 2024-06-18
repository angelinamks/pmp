package com.example.caloriescounter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.caloriescounter.databinding.ActivityGraphBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.text.SimpleDateFormat
import java.util.*

class GraphActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGraphBinding
    private val PREFS_NAME = "calorie_entries"
    lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        barChart = binding.barChart
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val entries = ArrayList<BarEntry>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // Отримання даних за останні 7 днів
        for (i in 0..6) {
            val date = dateFormat.format(calendar.time)
            val calorieEntries = sharedPreferences.getStringSet(date, setOf()) ?: setOf()
            val totalCalories = calorieEntries.sumOf { it.split(":")[1].trim().split(" ")[0].toInt() }

            entries.add(BarEntry(i.toFloat(), totalCalories.toFloat()))
            calendar.add(Calendar.DAY_OF_YEAR, -1)
        }

        val dataSet = BarDataSet(entries, "Калорії")
        val data = BarData(dataSet)
        barChart.data = data
        barChart.invalidate() // refresh

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
