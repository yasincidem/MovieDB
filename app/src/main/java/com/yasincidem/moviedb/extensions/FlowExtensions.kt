package com.yasincidem.moviedb.extensions

import android.util.Log
import com.yasincidem.moviedb.core.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun <T> Flow<Resource<T>>.onEachResource(
    onSuccess: suspend (T) -> Unit = {},
    onError: suspend (Throwable) -> Unit = {},
    onLoading: suspend () -> Unit = {},
    onEachResource: suspend (Resource<T>) -> Unit = {}
): Flow<Resource<T>> = onEach {
    if (it is Resource.Error) {
        onError(it.exception)
    }
    if (it is Resource.Success) {
        onSuccess(it.data)
    }
    if (it is Resource.Loading) {
        onLoading()
    }
    onEachResource(it)
}

fun <T> Flow<T>.safeLaunchIn(scope: CoroutineScope): Job = scope.launch {
    catch { Log.e("error", it.message.toString()) }.collect()
}

fun <T> Flow<T>.remote(withLoading: Boolean = true): Flow<Resource<T>> {
    return map<T, Resource<T>> { Resource.Success(it) }
        .onStart {
            if (withLoading) {
                emit(Resource.Loading)
            }
        }
        .catch {
            emit(Resource.Error(it))
        }
        //issue/KT-48031
        .map { it }
}

fun <T, R> Flow<Resource<T>>.mapData(mapper: (T) -> R): Flow<Resource<R>> {
    return map { it.map(mapper) }.catch { emit(Resource.Error(it)) }
}
