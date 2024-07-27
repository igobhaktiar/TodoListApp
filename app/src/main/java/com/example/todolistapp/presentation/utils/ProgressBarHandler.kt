package com.example.todolistapp.presentation.utils

import android.view.View
import android.widget.ProgressBar

class ProgressBarHandler(private val progressBar: ProgressBar) {
    fun showProgressBar() {
        // Show the progress bar
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        // Hide the progress bar
        progressBar.visibility = View.GONE
    }
}