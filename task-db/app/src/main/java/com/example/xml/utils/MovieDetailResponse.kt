package com.example.xml.utils

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    val id: Int,
    val runtime: Int,
    val tagline: String?,
    val budget: Long,
    val homepage: String?
)