package com.pritom.dutta.movie.domain.repository.tv_series

import com.pritom.dutta.movie.domain.models.TVShow
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NowShowingTVRepository {
    suspend fun fetchNowShowingTV(): Flow<NetworkResult<TmdbWrapperModel<TVShow>>>
}