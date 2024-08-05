package com.example.todolistapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.model.User
import com.example.todolistapp.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    // Function to check if the user is logged in
    fun isLoggedIn(): Boolean {
        return authUseCase.isLoggedIn()
    }

    // Function to get user data
    fun getUserData(): User {
        return authUseCase.getUser()
    }

    // Function to log out
    fun logOut() {
        authUseCase.logout()
        authUseCase.saveLoginState(false)
    }
}