package com.darklabs.darknews.shared.util

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    object Loading : Result<Nothing>()
    class Error(error: Throwable) : Result<Nothing>()
}