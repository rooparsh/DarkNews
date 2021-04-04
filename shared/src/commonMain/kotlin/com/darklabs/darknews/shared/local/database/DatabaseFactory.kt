package com.darklabs.darknews.shared.local.database

import com.darklabs.darknews.cache.DarkNewsDatabase

class DatabaseFactory(private val driverFactory: DriverFactory) {
    fun createDatabase(): DarkNewsDatabase {
        return DarkNewsDatabase(driverFactory.createDriver())
    }
}