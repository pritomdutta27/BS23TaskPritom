package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.data.utils.onResponse
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 06/05/25.
 */

@OpenForTesting
class ApiRemoteDataSource @Inject constructor(private val api: MovieApiService) {
    suspend operator fun invoke(page: Int = 1) = api.fetchTopRatedMovie(page).onResponse()

    suspend fun invokeNowShowing(page: Int = 1) = api.fetchNowShowingMovie(page).onResponse()

    suspend fun invokeTrending(page: Int = 1) = api.fetchTrendingMovie(page).onResponse()

    suspend fun invokeDetails(id: Int) = api.fetchMovieDetails(id).onResponse()

    suspend fun invokeMovieActors(id: Int) = api.fetchMovieActors(id).onResponse()
}