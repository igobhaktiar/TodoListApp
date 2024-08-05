package com.example.todolistapp.data.source.remote

import com.example.todolistapp.data.model.User
import com.example.todolistapp.domain.usecase.UserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseAuthSource(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val userUseCase: UserUseCase
) {

    // Function to login
    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: throw Exception("Login failed")
            val id = firebaseUser.uid
            val user = userUseCase.getUser(id)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Function to register
    suspend fun register(email: String, password: String, username: String): Result<User> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: throw Exception("Register failed")
            val id = firebaseUser.uid
            val user = User(email, id, username)
            FirebaseUserFireStoreSource(firestore).addUserToFirStore(user, id)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}