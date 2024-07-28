package com.example.todolistapp.data.source.remote

import com.example.todolistapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseAuthSource(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    // Function to login
    suspend fun login(email: String, password: String): Result<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(true)
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
            FirebaseFireStoreSource(firestore).addUserToFirStore(user, id)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}