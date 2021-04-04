package com.darklabs.darknews.androidApp.di

import com.darklabs.darknews.cache.NewsDbQueries
import com.darklabs.darknews.shared.network.NewsApi
import com.darklabs.darknews.shared.repository.CountryRepository
import com.darklabs.darknews.shared.repository.CountryRepositoryImpl
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
    fun providesNewsRepository(newsApi: NewsApi, newsDbQueries: NewsDbQueries): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDbQueries)
    }

    @Provides
    @Singleton
    fun providesCountryRepository(): CountryRepository {
        return CountryRepositoryImpl()
    }
}