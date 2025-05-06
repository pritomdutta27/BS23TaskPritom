package com.pritom.dutta.movie.data.repositoryImp.movie.details

import com.pritom.dutta.movie.data.datasource.remote.ApiRemoteDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.movie.details.DetailsMovieRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailsMovieRepositoryImp @Inject constructor(private val api: ApiRemoteDataSource) :
    DetailsMovieRepository {

    override suspend fun fetchDetailsMovies(id: Int) =
        api.invokeDetails(id).onException().flowOn(IO)
}