package com.example.todolistapp.domain.repository

import com.example.todolistapp.data.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(email: String, password: String, username: String): Result<User>
    fun logout()
    fun isLoggedIn(): Boolean
    fun getUser(): User
    fun saveUser(user: User)
    fun saveLoginState(isLoggedIn: Boolean)
}