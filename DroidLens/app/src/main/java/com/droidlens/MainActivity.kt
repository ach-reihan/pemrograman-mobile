package com.droidlens

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        val pager = findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.pager)
        pager.adapter = MainPagerAdapter(this)

        val tabs = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tabs)
        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.setText(if (position == 0) R.string.tab_history else R.string.tab_architecture)
        }.attach()
    }
}