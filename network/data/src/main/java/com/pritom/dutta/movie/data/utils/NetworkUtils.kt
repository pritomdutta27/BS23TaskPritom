package com.pritom.dutta.movie.data.utils

import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Pritom Dutta on 06/05/25.
 */
const val SOCKET_TIME_OUT_EXCEPTION =
    "Request timed out while trying to connect. Please ensure you have a strong signal and try again."
const val UNKNOWN_NETWORK_EXCEPTION =
    "An unexpected error has occurred. Please check your network connection and try again."
const val CONNECT_EXCEPTION =
    "Could not connect to the server. Please check your internet connection and try again."
const val UNKNOWN_HOST_EXCEPTION =
    "Couldn't connect to the server at the moment. Please try again in a few minutes."

fun <T : Any> Response<T>.onResponse(): Flow<T> {
    return flow {
        if (isSuccessful) {
            if (body() != null) {
                emit(body()!!)
            } else {
                throw ApiException(code(), null, message())
            }
        } else {
            throw ApiException(code(), errorBody(), message())
        }
    }
}

fun <T : Any> Flow<T>.onException(): Flow<NetworkResult<T>> {
    return this.map<T, NetworkResult<T>> {
        NetworkResult.Success(it)
    }.onStart {
        emit(NetworkResult.Loading())
    }.catch { exception ->
        if (exception is ApiException) {
            val error = ErrorHandler.parseRequestException(
                exception.statusCode,
                exception.errorBody,
                exception.message
            )
            emit(NetworkResult.Error(error.statusCode, error.message))
        } else {
            when (exception) {
                is ConnectException -> {
                    emit(NetworkResult.Error(code = 404,message = CONNECT_EXCEPTION))
                }

                is UnknownHostException -> {
                    emit(NetworkResult.Error(code = 404,message = UNKNOWN_HOST_EXCEPTION))
                }

                is SocketTimeoutException -> {
                    emit(NetworkResult.Error(code = 404,message = SOCKET_TIME_OUT_EXCEPTION))
                }

                is HttpException -> {
                    emit(NetworkResult.Error(code = 404,message = UNKNOWN_NETWORK_EXCEPTION))
                }

                else -> {
                    emit(NetworkResult.Error(code = 404,message = UNKNOWN_NETWORK_EXCEPTION))
                }
            }
        }
    }
}