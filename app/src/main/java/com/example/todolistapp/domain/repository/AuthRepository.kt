package com.example.todolistapp.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(email: String, password: String): Result<Boolean>
}