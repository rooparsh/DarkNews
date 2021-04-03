package com.darklabs.darknews.androidApp.di

import android.content.Context
import com.darklabs.darknews.cache.DarkNewsDatabase
import com.darklabs.darknews.shared.local.database.DatabaseFactory
import com.darklabs.darknews.shared.local.database.DriverFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun providesDriverFactory(@ApplicationContext context: Context): DriverFactory {
        return DriverFactory(context)
    }

    @Singleton
    @Provides
    fun providesDatabase(driverFactory: DriverFactory): DarkNewsDatabase {
        return DatabaseFactory(driverFactory).createDatabase()
    }
}