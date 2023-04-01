package com.example.weather.domain.usecase

import com.example.weather.data.model.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [ApiResponse.Error] to the result) is the subclasses's responsibility.
 */
abstract class BaseUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<NetworkResult<R>> = execute(parameters)
        .catch { e -> emit(NetworkResult.Failure(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<NetworkResult<R>>
}