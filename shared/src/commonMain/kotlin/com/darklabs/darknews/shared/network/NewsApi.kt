package com.darklabs.darknews.shared.network

import com.darklabs.darknews.shared.network.data.News
import io.ktor.client.*
import io.ktor.client.request.*

class NewsApi(private val client: HttpClient) {

    suspend fun getTopHeadlines(country: String): News {
        return client.get(GET_TOP_HEADLINES) { parameter("country", country) }
    }
}