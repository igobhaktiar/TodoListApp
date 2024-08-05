package com.example.todolistapp.domain.usecase

import com.example.todolistapp.data.model.User
import com.example.todolistapp.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {
    suspend fun login(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }

    suspend fun register(email: String, password: String, username: String): Result<User> {
        return authRepository.register(email, password, username)
    }

    fun logout() {
        return authRepository.logout()
    }

    fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn()
    }

    fun getUser(): User {
        return authRepository.getUser()
    }

    fun saveUser(user: User) {
        return authRepository.saveUser(user)
    }

    fun saveLoginState(isLoggedIn: Boolean) {
        return authRepository.saveLoginState(isLoggedIn)
    }

    fun getUserData(): User {
        return authRepository.getUser()
    }

    fun saveUserData(user: User) {
        return authRepository.saveUser(user)
    }
}