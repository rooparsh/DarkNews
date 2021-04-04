package com.darklabs.darknews.shared.repository

actual interface CountryRepository {
    actual fun getCountries(): Map<String, String>
}

actual class CountryRepositoryImpl : CountryRepository {
    actual override fun getCountries(): Map<String, String> {
        return mapOf()
    }

}