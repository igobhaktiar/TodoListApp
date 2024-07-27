package com.example.todolistapp.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.model.User
import com.example.todolistapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    // RegisterResult class
    private val _registerResult = MutableLiveData<Result<User>>()

    // Success and error states
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    // Error message
    val errorMessage: LiveData<String> = _registerResult.map {
        it.exceptionOrNull()?.message ?: ""
    }


    // Function to register
    fun register(email: String, password: String, username: String) {
        viewModelScope.launch {
            _registerResult.value = userUseCase.register(email, password, username)

            // If registration is successful, set the success state to true
            if (_registerResult.value?.isSuccess == true) {
                _isSuccess.value = true
            } else {
                // If registration is unsuccessful, set the success state to false
                _isSuccess.value = false
            }
        }
    }
}