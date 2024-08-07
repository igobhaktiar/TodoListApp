package com.example.todolistapp.data.model

data class User(
    val email: String = "",
    val id: String = "",
    val username: String = ""
) {
    // No-argument constructor for deserialization
    constructor() : this("", "", "")
}
