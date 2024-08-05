package com.example.todolistapp.domain.repository

import com.example.todolistapp.data.model.User

interface UserRepository {

    suspend fun getUser(id: String): User

}