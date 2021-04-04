package com.darklabs.darknews.shared.network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("status") val status: String = "",
    @SerialName("totalResults") val totalResults: Int = 0,
    @SerialName("articles") val articles: List<Article> = emptyList()
)
