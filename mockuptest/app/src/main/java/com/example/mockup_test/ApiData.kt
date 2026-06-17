package com.example.mockup_test

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val message: String,
    val code: String,
    val data: T
)

@Serializable
data class UserProfile(
    val id: Int,
    val name: String,
    val email: String
)