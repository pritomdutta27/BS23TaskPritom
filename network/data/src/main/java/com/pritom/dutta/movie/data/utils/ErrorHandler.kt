package com.pritom.dutta.movie.data.utils

import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

/**
 * Created by Pritom Dutta on 06/05/25.
 */
object ErrorHandler {

    /**
     * parse [RequestException] from [okhttp3.OkHttpClient] response
     */
    fun parseRequestException(
        statusCode: Int,
        errorBody: ResponseBody? = null,
        message: String? = null
    ): RequestException {
        errorBody?.let { body ->
            val moshi = Gson()
//            val jsonAdapter: JsonAdapter<Error> = moshi.adapter(Error::class.java)
            val apiError = moshi.fromJson(body.string(), Error::class.java)

            // if error response does not contain any specific message use a generic error message from resource
            return RequestException().apply {
                this.message =
                    apiError?.status_message ?: UNKNOWN_NETWORK_EXCEPTION
                this.statusCode = statusCode
            }
        }

        message?.let { msg ->
            return RequestException(message = msg)
        }
        return RequestException(message = UNKNOWN_NETWORK_EXCEPTION)
    }
}