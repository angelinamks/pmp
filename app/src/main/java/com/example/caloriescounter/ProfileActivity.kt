package com.example.caloriescounter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.caloriescounter.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val PREFS_NAME = "user_profile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Завантаження даних профілю
        binding.etName.setText(sharedPreferences.getString("name", ""))
        binding.etGender.setText(sharedPreferences.getString("gender", ""))
        binding.etAge.setText(sharedPreferences.getInt("age", 0).toString())
        binding.etHeight.setText(sharedPreferences.getFloat("height", 0f).toString())
        binding.etWeight.setText(sharedPreferences.getFloat("weight", 0f).toString())

        binding.btnSaveProfile.setOnClickListener {
            val name = binding.etName.text.toString()
            val gender = binding.etGender.text.toString()
            val age = binding.etAge.text.toString().toInt()
            val height = binding.etHeight.text.toString().toFloat()
            val weight = binding.etWeight.text.toString().toFloat()

            // Збереження даних профілю
            with(sharedPreferences.edit()) {
                putString("name", name)
                putString("gender", gender)
                putInt("age", age)
                putFloat("height", height)
                putFloat("weight", weight)
                apply()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
