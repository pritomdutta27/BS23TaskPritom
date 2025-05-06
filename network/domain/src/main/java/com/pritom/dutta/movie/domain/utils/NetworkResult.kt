package com.pritom.dutta.movie.domain.utils

/**
 * Created by Pritom Dutta on 11/5/24.
 */

sealed class NetworkResult<out T>(
    val data: T? = null,
    val code: Int? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(code: Int? = 404, message: String?, data: T? = null) :
        NetworkResult<T>(data, code, message)

    class Loading<T> : NetworkResult<T>()
}
