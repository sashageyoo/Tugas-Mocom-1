package com.example.barangapp.data

data class User(
    val id: String,
    val username: String,
    val password: String
)

object Users {

    private val users = mutableListOf<User>()

    private var lastLoggedIn: User? = null   // ini yang benar

    fun addUser(user: User) {
        users.add(user)
    }

    fun setLoggedInUser(user: User) {
        lastLoggedIn = user
    }

    fun getUsername(): String {
        return lastLoggedIn?.username ?: "Guest"
    }
}