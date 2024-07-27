package com.example.todolistapp.domain.usecase

import com.example.todolistapp.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend fun login(email: String, password: String): Result<Boolean> {
        return authRepository.login(email, password)
    }

    suspend fun register(email: String, password: String): Result<Boolean> {
        return authRepository.register(email, password)
    }
}