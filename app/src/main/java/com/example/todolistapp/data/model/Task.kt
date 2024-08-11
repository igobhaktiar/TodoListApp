package com.example.todolistapp.data.model

data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val dueDate: String = "",
    val reminder: Int = 0,
    val attachFileUrl: String? = null
) {
    // No-argument constructor for deserialization
    constructor() : this("", "", "", false, "", 0, null)
}
