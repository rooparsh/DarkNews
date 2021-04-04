package com.darklabs.darknews.androidApp.di

import com.darklabs.darknews.shared.network.NetworkClient
import com.darklabs.darknews.shared.network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesNetworkClient(): HttpClient {
        return NetworkClient.client
    }

    @Singleton
    @Provides
    fun providesNewsApiService(httpClient: HttpClient): NewsApi {
        return NewsApi(httpClient)
    }

}