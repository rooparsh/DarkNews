package com.darklabs.darknews.shared.repository

import com.darklabs.darknews.cache.NewsDbQueries
import com.darklabs.darknews.cache.NewsTable
import com.darklabs.darknews.shared.network.NewsApi
import com.darklabs.darknews.shared.util.Result
import com.darklabs.darknews.shared.util.networkBoundResource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsHeadlines(country: String): Flow<Result<List<NewsTable>>>
}

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDbQueries: NewsDbQueries
) : NewsRepository {

    override fun getNewsHeadlines(country: String): Flow<Result<List<NewsTable>>> =
        networkBoundResource({
            newsDbQueries.getAllNews().asFlow().mapToList()
        }, {
            newsApi.getTopHeadlines(country)
        }, {
            it.articles.forEach { article ->
                newsDbQueries.transaction {
                    newsDbQueries.insertNews(
                        article.source.id,
                        article.author,
                        article.source.name,
                        article.title,
                        article.description,
                        article.url,
                        article.urlToImage,
                        article.publishedAt,
                        article.content
                    )
                }

            }
        }
        )

}
