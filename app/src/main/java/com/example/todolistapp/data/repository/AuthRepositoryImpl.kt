package com.example.todolistapp.data.repository

import com.example.todolistapp.data.model.User
import com.example.todolistapp.data.source.remote.FirebaseAuthSource
import com.example.todolistapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> {
        return FirebaseAuthSource(firebaseAuth, firestore).login(email, password)
    }

    override suspend fun register(email: String, password: String, username: String): Result<User> {
        return FirebaseAuthSource(firebaseAuth, firestore).register(email, password, username)
    }
}