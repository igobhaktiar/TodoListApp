package com.example.todolistapp.data.source.remote

import com.example.todolistapp.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseFireStoreSource(
    private val firestore: FirebaseFirestore
) {

    // Function add user to firestore
    suspend fun addUserToFirStore(user: User, id: String) {
        firestore.collection("users").document(id).set(user).await()
    }
}