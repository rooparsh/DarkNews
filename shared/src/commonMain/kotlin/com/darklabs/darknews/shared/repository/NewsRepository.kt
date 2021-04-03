package com.darklabs.darknews.shared.repository

import com.darklabs.darknews.cache.DarkNewsDatabase
import com.darklabs.darknews.cache.NewsTable
import com.darklabs.darknews.shared.network.NewsApi
import com.darklabs.darknews.shared.util.Result
import com.darklabs.darknews.shared.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface NewsRepository {
    fun getNewsHeadlines(country: String): Flow<Result<List<NewsTable>>>
}

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val database: DarkNewsDatabase
) : NewsRepository {

    override fun getNewsHeadlines(country: String): Flow<Result<List<NewsTable>>> =
        networkBoundResource(
            {
                flowOf(database.newsDbQueries.getAllNews().executeAsList())
            },
            {
                newsApi.getTopHeadlines(country)
            },
            {
                it.articles.forEach { article ->
                    database.newsDbQueries.insertNews(
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
        )

}
