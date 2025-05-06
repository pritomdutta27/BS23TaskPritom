package com.pritom.dutta.movie.domain.repository.tv_series

import com.pritom.dutta.movie.domain.models.TVShow
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow


/**
 * Created by Pritom Dutta on 11/5/24.
 */
interface TopRatedTVRepository {
    suspend fun fetchTopRatedTvSeries(): Flow<NetworkResult<TmdbWrapperModel<TVShow>>>
}