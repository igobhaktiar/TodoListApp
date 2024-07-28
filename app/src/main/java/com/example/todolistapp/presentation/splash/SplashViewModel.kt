package com.example.todolistapp.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.source.local.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : ViewModel() {

    // Function to check if the user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPreferenceHelper.getLoginState()
    }

}