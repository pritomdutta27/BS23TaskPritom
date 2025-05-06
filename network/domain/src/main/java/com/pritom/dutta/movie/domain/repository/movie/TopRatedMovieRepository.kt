package com.pritom.dutta.movie.domain.repository.movie

import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow


/**
 * Created by Pritom Dutta on 11/5/24.
 */
interface TopRatedMovieRepository {
    suspend fun fetchTopRatedMovies(): Flow<NetworkResult<TmdbWrapperModel<Movie>>>
}