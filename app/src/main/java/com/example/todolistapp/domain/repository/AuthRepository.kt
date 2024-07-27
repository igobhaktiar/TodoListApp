package com.example.todolistapp.domain.repository

import com.example.todolistapp.data.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(email: String, password: String, username: String): Result<User>
}