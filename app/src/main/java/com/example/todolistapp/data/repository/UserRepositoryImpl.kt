package com.example.todolistapp.data.repository

import com.example.todolistapp.data.model.User
import com.example.todolistapp.data.source.remote.FirebaseUserFireStoreSource
import com.example.todolistapp.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    override suspend fun getUser(id: String): User {
        try {
            val user = FirebaseUserFireStoreSource(firestore).getUserFromFireStore(id)
            return user
        } catch (e: Exception) {
            throw e
        }
    }


}