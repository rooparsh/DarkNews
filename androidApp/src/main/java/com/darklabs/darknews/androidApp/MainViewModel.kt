package com.darklabs.darknews.androidApp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.darklabs.darknews.shared.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    newsRepository: NewsRepository
) : ViewModel() {
    val news = newsRepository.getNewsHeadlines("us")
}