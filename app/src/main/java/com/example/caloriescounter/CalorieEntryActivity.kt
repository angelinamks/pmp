package com.example.caloriescounter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.caloriescounter.databinding.ActivityCalorieEntryBinding
import java.text.SimpleDateFormat
import java.util.*

class CalorieEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalorieEntryBinding
    private val PREFS_NAME = "calorie_entries"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        binding.btnSaveCalories.setOnClickListener {
            val foodName = binding.etFoodName.text.toString()
            val calories = binding.etCalories.text.toString().toInt()
            val datePicker = binding.datePicker

            val calendar = Calendar.getInstance()
            calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)

            // Збереження даних калорій
            val calorieEntries = sharedPreferences.getStringSet(date, mutableSetOf()) ?: mutableSetOf()
            calorieEntries.add("$foodName: $calories калорій")

            with(sharedPreferences.edit()) {
                putStringSet(date, calorieEntries)
                apply()
            }

            // Спливаюче повідомлення
            Toast.makeText(this, "Калорії додані!", Toast.LENGTH_SHORT).show()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
