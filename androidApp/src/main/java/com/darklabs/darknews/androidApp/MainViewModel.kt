package com.darklabs.darknews.androidApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.darklabs.darknews.shared.repository.CountryRepository
import com.darklabs.darknews.shared.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    newsRepository: NewsRepository,
    private val countryRepository: CountryRepository
) : ViewModel() {

    private var _countriesMap = MutableLiveData<Map<String, String>>()
    val countries = _countriesMap as LiveData<Map<String, String>>

    val country = mutableStateOf("in")
    val news = newsRepository.getNewsHeadlines(country.value)

    val dialogState by lazy { mutableStateOf(false) }

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            _countriesMap.postValue(countryRepository.getCountries())
        }
    }
}