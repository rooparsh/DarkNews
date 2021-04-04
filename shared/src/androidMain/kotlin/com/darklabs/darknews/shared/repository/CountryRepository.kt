package com.darklabs.darknews.shared.repository

import java.util.*

actual interface CountryRepository {
    actual fun getCountries(): Map<String, String>
}

actual class CountryRepositoryImpl : CountryRepository {

    actual override fun getCountries(): Map<String, String> {
        val countriesMap = hashMapOf<String, String>()
        val isoCountries = Locale.getISOCountries()
        isoCountries.forEach {
            val locale = Locale("", it)
            countriesMap[locale.country.toLowerCase(Locale.getDefault())] = locale.displayCountry
        }
        return countriesMap.toList().sortedBy { (_, value) -> value }.toMap()
    }

}