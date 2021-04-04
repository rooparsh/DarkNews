package com.darklabs.darknews.shared.local.database

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}