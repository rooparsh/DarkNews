package com.darklabs.darknews.shared.network

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

object NetworkClient {
    private const val BASE_URL = "newsapi.org"

    val client: HttpClient = HttpClient {
        install(JsonFeature) {
            serializer =
                KotlinxSerializer(kotlinx.serialization.json.Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
            }
            header("Authorization", "ec9b64e47f294778a8e99d858213c5cc")
        }
    }
}