package com.pritom.dutta.movie.data.repositoryImp.movie

import com.pritom.dutta.movie.data.datasource.remote.ApiRemoteDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.movie.NowShowingMovieRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class NowShowingMovieRepositoryImp @Inject constructor(private val api: ApiRemoteDataSource) :
    NowShowingMovieRepository {
    override suspend fun fetchNowShowingMovies() = api.invokeNowShowing().onException().flowOn(IO)
}