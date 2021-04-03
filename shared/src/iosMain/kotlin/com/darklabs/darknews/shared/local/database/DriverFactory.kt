package com.darklabs.darknews.shared.local.database

import com.darklabs.darknews.cache.DarkNewsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(DarkNewsDatabase.Schema, "news.db")
    }
}