package com.pritom.dutta.movie.domain.repository

import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun fetchStockData(page: Int): Flow<NetworkResult<List<ShowDisplayStockData>>>
}