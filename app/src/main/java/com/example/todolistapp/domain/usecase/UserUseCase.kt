package com.example.todolistapp.domain.usecase

import com.example.todolistapp.domain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    suspend fun getUser(id: String) = userRepository.getUser(id)
}