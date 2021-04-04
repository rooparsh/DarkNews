package com.darklabs.darknews.shared.repository


expect interface CountryRepository {
    fun getCountries(): Map<String, String>
}

expect class CountryRepositoryImpl : CountryRepository {

    override fun getCountries(): Map<String, String>
}
