package com.example.todolistapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.source.local.SharedPreferenceHelper
import com.example.todolistapp.domain.usecase.UserUseCase
import com.example.todolistapp.presentation.utils.GenericState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : ViewModel() {

    // LoginState class
    private val _loginState = MutableLiveData<GenericState>()
    val loginState = _loginState

    // LoginResult class
    private val _loginResult = MutableLiveData<Result<Boolean>>()

    // Error message
    val errorMessage: LiveData<String> = _loginResult.map {
        it.exceptionOrNull()?.message ?: ""
    }

    // Function to login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = GenericState.Loading
            _loginResult.value = userUseCase.login(email, password)
            // If login is successful, set the login state to success
            if (_loginResult.value?.isSuccess == true && _loginResult.value?.getOrNull() == true) {
                _loginState.value = GenericState.Success
                sharedPreferenceHelper.saveEmail(email)
                sharedPreferenceHelper.saveLoginState(true)
            } else {
                // If login is unsuccessful, set the login state to error
                _loginState.value = GenericState.Error

            }
        }
    }
}