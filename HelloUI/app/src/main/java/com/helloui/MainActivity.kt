package com.helloui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputField = findViewById<EditText>(R.id.etInput)
        val showButton = findViewById<Button>(R.id.btnShow)
        val resultText = findViewById<TextView>(R.id.tvResult)
        val themeButton = findViewById<ImageButton>(R.id.btnTheme)
        val currentMode = AppCompatDelegate.getDefaultNightMode()

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            themeButton.setImageResource(R.drawable.ic_sun)
        } else {
            themeButton.setImageResource(R.drawable.ic_moon)
        }

        themeButton.setOnClickListener {
            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        showButton.setOnClickListener {
            val name = inputField.text.toString()
            if (name.isNotBlank()) {
                resultText.text = "Hello $name!"
            } else {
                resultText.text = "Hello!"
            }
        }

        themeButton.setOnClickListener {
            val currentMode = AppCompatDelegate.getDefaultNightMode()
            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}