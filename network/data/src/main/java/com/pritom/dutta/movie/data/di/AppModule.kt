package com.pritom.dutta.movie.data.di

import android.app.Application
import android.content.Context
import com.pritom.dutta.movie.data.di.annotations.AppBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Pritom Dutta on 06/05/25.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @AppBaseUrl
    fun provideBaseUrl(): String = "https://api.themoviedb.org/3/"
}