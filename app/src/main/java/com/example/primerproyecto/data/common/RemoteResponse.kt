package com.example.primerproyecto.data.common

import com.example.primerproyecto.utils.AsyncResult

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal inline fun <T> remoteResponse(crossinline block: suspend () -> T): RepositoryResponse<T> {
    return object : RemoteResponse<T>() {

        override suspend fun requestRemoteCall(): T = block()

    }.build()
}

abstract class RemoteResponse<ResultType> {

    private var flow: Flow<AsyncResult<ResultType>>? = null
    private var deferredValue: Deferred<AsyncResult<ResultType>>? = null

    //region RepositoryResponse
    private val response = object : RepositoryResponse<ResultType> {
        override suspend fun flow() = executeFlow()
        override suspend fun valueAsync() = executeAsync()
    }
    //endregion

    //region Logic Template
    fun build(): RepositoryResponse<ResultType> {
        return response
    }

    private suspend fun executeFlow(): Flow<AsyncResult<ResultType>> {
        return flow ?: flow {
            executeBase { emit(it) }
        }.apply { flow = this }
    }

    private suspend fun executeAsync(): Deferred<AsyncResult<ResultType>> {
        return deferredValue ?: coroutineScope {
            async {
                executeBase { }
            }
        }.apply { deferredValue = this }
    }

    private suspend fun executeBase(emit: suspend (AsyncResult<ResultType>) -> Unit): AsyncResult<ResultType> {
        emit(AsyncResult.Loading(null))
        val finalValue: AsyncResult<ResultType>
        finalValue =
            try {
                val networkResponse = requestRemoteCall()
                delay(2000)
                AsyncResult.Success(networkResponse)
            } catch (e: Exception) {
                AsyncResult.Error("Ha petau", null)
            }
        emit(finalValue)
        return finalValue
    }


    protected abstract suspend fun requestRemoteCall(): ResultType
}
