package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.MovieDetailsModel
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.models.cast.CastResponseModel
import com.pritom.dutta.movie.domain.utils.DataSourceConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Pritom Dutta on 06/05/25.
 */
interface MovieApiService {

    @GET("movie/popular?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchTopRatedMovie(@Query("page") page: Int): Response<TmdbWrapperModel<Movie>>

    @GET("movie/now_playing?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchNowShowingMovie(@Query("page") page: Int): Response<TmdbWrapperModel<Movie>>

    @GET("trending/movie/day?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchTrendingMovie(@Query("page") page: Int): Response<TmdbWrapperModel<Movie>>

    @GET("movie/{movie_id}?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsModel>

    @GET("movie/{movie_id}/credits?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchMovieActors(@Path("movie_id") movieId: Int): Response<CastResponseModel>

}