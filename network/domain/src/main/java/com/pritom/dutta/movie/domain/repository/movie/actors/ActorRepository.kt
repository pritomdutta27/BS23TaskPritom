package com.pritom.dutta.movie.domain.repository.movie.actors

import com.pritom.dutta.movie.domain.models.cast.CastResponseModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ActorRepository {
    suspend fun fetchMovieActors(id: Int): Flow<NetworkResult<CastResponseModel>>
}