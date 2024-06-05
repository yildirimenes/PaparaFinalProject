package com.enons.paparaproject.data.remote.dto

data class Message(
    val content: String,
    val role: String,
) {
    val isUser: Boolean
        get() = role == "user"
}