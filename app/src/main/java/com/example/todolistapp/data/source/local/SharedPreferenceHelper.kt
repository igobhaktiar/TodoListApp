package com.example.todolistapp.data.source.local

import android.content.Context
import com.example.todolistapp.data.model.User


class SharedPreferenceHelper(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("todo_list", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
    }

    // Function to save login state
    fun saveLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    // Function to get login state
    fun getLoginState(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    // Function to save user data
    fun saveUserData(user: User) {
        sharedPreferences.edit().putString(KEY_EMAIL, user.email).apply()
        sharedPreferences.edit().putString(KEY_USERNAME, user.username).apply()
        sharedPreferences.edit().putString(KEY_USER_ID, user.id).apply()
    }

    // Function to get user data
    fun getUserData(): User {
        val email = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
        val username = sharedPreferences.getString(KEY_USERNAME, "") ?: ""
        val id = sharedPreferences.getString(KEY_USER_ID, "") ?: ""
        return User(email, id, username)
    }


    // Function to clear user data
    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }

}