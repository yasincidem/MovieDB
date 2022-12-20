package com.yasincidem.moviedb.core

sealed class Resource<out T> {
    object FirstLoading : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()

    fun <R> map(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(
            transform(data)
        )

        is Error -> Error(
            exception
        )

        is Loading -> Loading
        is FirstLoading -> FirstLoading
    }
}