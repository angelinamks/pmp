package com.example.caloriescounter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.caloriescounter.databinding.ActivityCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding
    private val PREFS_NAME = "calorie_entries"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Отримання даних калорій за обраний день
            val selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
            val calorieEntries = sharedPreferences.getStringSet(selectedDate, setOf()) ?: setOf()

            binding.tvCalorieDetails.text = "Деталі калорій за $selectedDate\n" + calorieEntries.joinToString("\n")
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
