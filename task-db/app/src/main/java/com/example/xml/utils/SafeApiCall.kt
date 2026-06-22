package com.example.xml.utils

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

object KtorClient {
    val client: HttpClient = HttpClient(Android) {

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

fun <T> safeApiCall(apiCall: suspend () -> T): Flow<NetworkResult<T>> = flow {
    emit(NetworkResult.Loading)
    try {
        val response = apiCall()
        emit(NetworkResult.Success(response))
    } catch (e: RedirectResponseException) { // 3xx
        emit(NetworkResult.Error("Redirect Error: ${e.response.status.description}", e))
    } catch (e: ClientRequestException) { // 4xx
        emit(NetworkResult.Error("Client Error: ${e.response.status.description}", e))
    } catch (e: ServerResponseException) { // 5xx
        emit(NetworkResult.Error("Server Error: ${e.response.status.description}", e))
    } catch (e: Exception) {
        emit(NetworkResult.Error("Unknown Error: ${e.localizedMessage}", e))
    }
}