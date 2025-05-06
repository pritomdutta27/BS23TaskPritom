package com.pritom.dutta.movie.data.repositoryImp.actors

import com.pritom.dutta.movie.data.datasource.remote.ApiRemoteDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.movie.actors.ActorRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class ActorRepositoryImp @Inject constructor(private val api: ApiRemoteDataSource) :
    ActorRepository {
    override suspend fun fetchMovieActors(id: Int) = api.invokeMovieActors(id).onException().flowOn(IO)
}