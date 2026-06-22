package com.example.xml.utils

sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    data class Success<out T>(final val data: T) : NetworkResult<T>()
    data class Error(final val message: String, final val cause: Throwable? = null) : NetworkResult<Nothing>()
}

