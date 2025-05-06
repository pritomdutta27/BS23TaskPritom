package com.pritom.dutta.movie.data.di

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Pritom Dutta on 06/05/25.
 */
@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    private fun getLogInterceptors(isDebug: Boolean = false): Interceptor {
        val builder = LoggingInterceptor.Builder()
//            .addHeader("Authorization", "Bearer ${API_KEY}")
            .setLevel(if (isDebug) Level.BASIC else Level.NONE)
            .log(Platform.INFO)
            .tag("BS23 App")
            .request("Request")
            .response("Response")
        builder.isDebugAble = isDebug
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val timeOut = 30
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)

        httpClient.addInterceptor(getLogInterceptors(true))
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}