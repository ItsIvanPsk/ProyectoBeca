package com.example.primerproyecto.data.common

import com.example.primerproyecto.utils.AsyncResult
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface RepositoryResponse<out ResultType> {

    suspend fun flow(): Flow<AsyncResult<ResultType>>

    suspend fun valueAsync(): Deferred<AsyncResult<ResultType>>

}