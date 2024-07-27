package com.example.todolistapp.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    // LoginState class
    private val _loginState = MutableLiveData<LoginState>()
    val loginState = _loginState

    // LoginResult class
    private val _loginResult = MutableLiveData<Result<Boolean>>()
    val loginResult: LiveData<Result<Boolean>> = _loginResult

    // Function to login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            _loginResult.value = loginUseCase.login(email, password)

            // If login is successful, set the login state to success
            if (_loginResult.value == Result.success(true)) {
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Error
            }
        }
    }
}