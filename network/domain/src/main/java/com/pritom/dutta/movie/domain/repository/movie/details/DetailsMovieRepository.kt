package com.pritom.dutta.movie.domain.repository.movie.details

import com.pritom.dutta.movie.domain.models.MovieDetailsModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DetailsMovieRepository {
    suspend fun fetchDetailsMovies(id: Int): Flow<NetworkResult<MovieDetailsModel>>
}