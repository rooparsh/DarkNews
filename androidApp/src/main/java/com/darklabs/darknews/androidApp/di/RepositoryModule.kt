package com.darklabs.darknews.androidApp.di

import com.darklabs.darknews.cache.DarkNewsDatabase
import com.darklabs.darknews.shared.network.NewsApi
import com.darklabs.darknews.shared.repository.NewsRepository
import com.darklabs.darknews.shared.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi, database: DarkNewsDatabase): NewsRepository {
        return NewsRepositoryImpl(newsApi, database)
    }
}