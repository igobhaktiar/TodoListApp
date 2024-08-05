package com.example.todolistapp.data.repository

import com.example.todolistapp.data.model.User
import com.example.todolistapp.data.source.local.SharedPreferenceHelper
import com.example.todolistapp.data.source.remote.FirebaseAuthSource
import com.example.todolistapp.domain.repository.AuthRepository
import com.example.todolistapp.domain.usecase.UserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val userUseCase: UserUseCase,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return FirebaseAuthSource(firebaseAuth, firestore, userUseCase).login(email, password)
    }

    override suspend fun register(email: String, password: String, username: String): Result<User> {
        return FirebaseAuthSource(firebaseAuth, firestore, userUseCase).register(
            email,
            password,
            username,
        )
    }

    override fun logout() {
        return sharedPreferenceHelper.clearUserData()
    }

    override fun isLoggedIn(): Boolean {
        return sharedPreferenceHelper.getLoginState()
    }

    override fun getUser(): User {
        return sharedPreferenceHelper.getUserData()
    }

    override fun saveUser(user: User) {
        return sharedPreferenceHelper.saveUserData(user)
    }

    override fun saveLoginState(isLoggedIn: Boolean) {
        return sharedPreferenceHelper.saveLoginState(isLoggedIn)
    }
}