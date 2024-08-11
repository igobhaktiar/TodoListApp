package com.example.todolistapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.todolistapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAction() // Initialize the action
        initUI() // Initialize the UI
    }

    private fun initUI() {
        enableEdgeToEdge() // Enable edge-to-edge display`
        setStatusBarColor() // Set the status bar color
        setUpBottomNav() // Set up the bottom navigation
    }

    private fun initAction() {
        binding.fab.setOnClickListener {
            // Navigate to the add task fragment
            findNavController(R.id.nav_host_fragment).navigate(R.id.formTaskFragment)
        }
    }

    private fun setStatusBarColor() {
        // Set the status bar color to white
        window.statusBarColor = resources.getColor(R.color.white)

        // Set the navigation bar color to white
        window.navigationBarColor = resources.getColor(R.color.white)

        // Set the navigation bar icon color to black
        ViewCompat.getWindowInsetsController(binding.root)?.isAppearanceLightNavigationBars = true

        // Set the status bar icon color to black
        ViewCompat.getWindowInsetsController(binding.root)?.isAppearanceLightStatusBars = true
    }

    private fun setUpBottomNav() {
        // Set the bottom navigation to navigate between fragments
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Hide the bottom navigation when the user is on the login fragment
            if (destination.id == R.id.homeFragment ||
                destination.id == R.id.profileFragment ||
                destination.id == R.id.galleryFragment ||
                destination.id == R.id.todoFragment
            ) {
                binding.bottomNavigation.visibility = View.VISIBLE
                binding.fab.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
                binding.fab.visibility = View.GONE
            }
        }
    }

}