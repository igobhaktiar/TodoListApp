package com.example.todolistapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.todolistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Set the status bar color to white
        window.statusBarColor = resources.getColor(R.color.white)

        // Set the navigation bar color to white
        window.navigationBarColor = resources.getColor(R.color.white)

        // Set the navigation bar icon color to black
        ViewCompat.getWindowInsetsController(binding.root)?.isAppearanceLightNavigationBars = true

        // Set the status bar icon color to black
        ViewCompat.getWindowInsetsController(binding.root)?.isAppearanceLightStatusBars = true

        // Set the bottom navigation to navigate between fragments
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

    }
}