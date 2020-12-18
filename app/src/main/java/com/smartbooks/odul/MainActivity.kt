package com.smartbooks.odul

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.smartbooks.odul.ui.PhrasebookFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    fun fragmentChanged() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (navHostFragment?.childFragmentManager?.fragments?.get(0) is PhrasebookFragment) {
            findViewById<TextView>(R.id.title_white).text = "Разговорник"
        } else {
            findViewById<TextView>(R.id.title_white).text = "Юкагирский язык"
        }
    }
}