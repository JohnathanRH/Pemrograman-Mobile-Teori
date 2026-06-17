package com.example.mockup_test

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object MockApiClient {

    private const val MOCK_JSON_RESPONSE = """
        {
            "message": "Success",
            "code": "200_OK",
            "data": {
                "id": 101,
                "name": "Jane Doe",
                "email": "janedoe@example.com"
            }
        }
    """

    val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                respond(
                    content = MOCK_JSON_RESPONSE,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }
}