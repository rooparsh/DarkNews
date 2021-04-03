package com.darklabs.darknews.shared.local.database

import android.content.Context
import com.darklabs.darknews.cache.DarkNewsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(DarkNewsDatabase.Schema, context, "news.db")
    }
}