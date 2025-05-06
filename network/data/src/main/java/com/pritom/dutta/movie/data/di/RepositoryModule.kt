package com.pritom.dutta.movie.data.di

import com.pritom.dutta.movie.data.repositoryImp.actors.ActorRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.movie.NowShowingMovieRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.movie.TopRatedMovieRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.movie.TrendingRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.movie.details.DetailsMovieRepositoryImp
import com.pritom.dutta.movie.domain.repository.movie.NowShowingMovieRepository
import com.pritom.dutta.movie.domain.repository.movie.TopRatedMovieRepository
import com.pritom.dutta.movie.domain.repository.movie.TrendingRepository
import com.pritom.dutta.movie.domain.repository.movie.actors.ActorRepository
import com.pritom.dutta.movie.domain.repository.movie.details.DetailsMovieRepository
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
    abstract fun provideTopRatedMovieRepository(api: TopRatedMovieRepositoryImp): TopRatedMovieRepository

    @Binds
    @Singleton
    abstract fun provideNowShowingMovieRepository(api: NowShowingMovieRepositoryImp): NowShowingMovieRepository

    @Binds
    @Singleton
    abstract fun provideTrendingRepository(api: TrendingRepositoryImp): TrendingRepository

    @Binds
    @Singleton
    abstract fun provideDetailsMovieRepository(api: DetailsMovieRepositoryImp): DetailsMovieRepository

    @Binds
    @Singleton
    abstract fun provideActorRepository(api: ActorRepositoryImp): ActorRepository

}