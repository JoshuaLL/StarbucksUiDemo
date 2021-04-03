package com.joshua.starbucksui

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.joshua.starbucksui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTransparentStatusBar()

        val navController = findNavController(R.id.nav_host_fragment)

        binding.navView.setupWithNavController(navController)
        binding.navView.background = null

        binding.fabScan.setOnClickListener {
            Log.i("MainActivity", "fabScan")
            binding.navView.selectedItemId = R.id.navigation_scan
        }
    }
}

