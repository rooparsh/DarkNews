package com.darklabs.darknews.shared.util

import kotlinx.coroutines.flow.*


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().firstOrNull()
    emit(Result.Loading)
    val flow = data?.let {
        if (shouldFetch(data)) {
            try {
                saveFetchResult(fetch())
                query().map { Result.Success(it) }
            } catch (throwable: Throwable) {
                query().map { Result.Error(throwable) }
            }
        } else {
            query().map { Result.Success(it) }
        }
    } ?: kotlin.run {
        saveFetchResult(fetch())
        query().map { Result.Success(it) }
    }
    emitAll(flow)
}