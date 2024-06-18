package com.example.caloriescounter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.caloriescounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.btnCalorieEntry.setOnClickListener {
            startActivity(Intent(this, CalorieEntryActivity::class.java))
        }

        binding.btnCalendar.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }

        binding.btnGraph.setOnClickListener {
            startActivity(Intent(this, GraphActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
