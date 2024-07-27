package com.example.todolistapp.presentation.login.viewmodel

open class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    object Error : LoginState()
}
