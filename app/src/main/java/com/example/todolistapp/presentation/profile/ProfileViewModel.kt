package com.example.todolistapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.source.local.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : ViewModel() {

    // Function to check if the user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPreferenceHelper.getLoginState()
    }

    // Function to log out
    fun logOut() {
        sharedPreferenceHelper.saveLoginState(false)
        sharedPreferenceHelper.clearUserData()

    }
}