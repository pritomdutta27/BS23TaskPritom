package com.pritom.dutta.movie.data.di

import android.content.Context
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import java.io.File
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
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
        val cache = Cache(File(context.cacheDir, "http_cache"), cacheSize)
        val cacheInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(15, TimeUnit.MINUTES) // Cache for 15 minutes
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }

        val timeOut = 30
        val httpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .addNetworkInterceptor(cacheInterceptor)
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)

        httpClient.addInterceptor(getLogInterceptors(true))
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
            val request = requestBuilder.build()
            chain.request()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}