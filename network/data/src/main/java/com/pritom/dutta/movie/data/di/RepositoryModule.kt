package com.pritom.dutta.movie.data.di

import com.pritom.dutta.movie.data.repositoryImp.StockRepositoryImp
import com.pritom.dutta.movie.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Pritom Dutta on 06/05/25.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideStockRepository(api: StockRepositoryImp): StockRepository

}