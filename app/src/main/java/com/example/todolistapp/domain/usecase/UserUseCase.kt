package com.example.todolistapp.domain.usecase

import com.example.todolistapp.data.model.User
import com.example.todolistapp.domain.repository.AuthRepository

class UserUseCase(private val authRepository: AuthRepository) {
    suspend fun login(email: String, password: String): Result<Boolean> {
        return authRepository.login(email, password)
    }

    suspend fun register(email: String, password: String, username: String): Result<User> {
        return authRepository.register(email, password, username)
    }
}