package com.example.todolistapp.data.source.remote

import com.example.todolistapp.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseUserFireStoreSource(
    private val firestore: FirebaseFirestore
) {

    // Function add user to firestore
    suspend fun addUserToFirStore(user: User, id: String) {
        firestore.collection("users").document(id).set(user).await()
    }

    // Function get user from firestore
    suspend fun getUserFromFireStore(id: String): User {
        val user = firestore.collection("users").document(id).get().await()
        return user.toObject(User::class.java)!!
    }
}