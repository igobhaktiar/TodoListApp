package com.example.todolistapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.source.local.SharedPreferenceHelper
import com.example.todolistapp.domain.usecase.AuthUseCase
import com.example.todolistapp.presentation.utils.GenericState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.todolistapp.data.model.User
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    // LoginState class
    private val _loginState = MutableLiveData<GenericState>()
    val loginState = _loginState

    // LoginResult class
    private val _loginResult = MutableLiveData<Result<User>>()

    // Error message
    val errorMessage: LiveData<String> = _loginResult.map {
        it.exceptionOrNull()?.message ?: ""
    }

    // Function to login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = GenericState.Loading
            _loginResult.value = authUseCase.login(email, password)
            // If login is successful, set the login state to success
            if (_loginResult.value?.isSuccess == true && _loginResult.value?.getOrNull() != null) {
                _loginState.value = GenericState.Success
                // Save the login state to shared preferences
                authUseCase.saveLoginState(true)
                // Save the user data to shared preference
                val user = _loginResult.value?.getOrNull()!!
                authUseCase.saveUserData(user)
            } else {
                // If login is unsuccessful, set the login state to error
                _loginState.value = GenericState.Error

            }
        }
    }
}