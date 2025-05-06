package com.pritom.dutta.movie.data.repositoryImp.movie

import com.pritom.dutta.movie.data.datasource.remote.ApiRemoteDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.movie.TopRatedMovieRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 11/5/24.
 */
@OpenForTesting
class TopRatedMovieRepositoryImp @Inject constructor(private val api: ApiRemoteDataSource) :
    TopRatedMovieRepository {
    override suspend fun fetchTopRatedMovies() = api.invoke().onException().flowOn(IO)
}