package com.pritom.dutta.movie.data.repositoryImp

import com.pritom.dutta.movie.data.datasource.remote.ApiRemoteDataSource
import com.pritom.dutta.movie.data.mapper.toShowDisplayStockResponse
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.StockRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StockRepositoryImp @Inject constructor(private val api: ApiRemoteDataSource) : StockRepository
 {
     override suspend fun fetchStockData(page: Int) = api.invoke(page = page).map { it.toShowDisplayStockResponse() }
         .onException().flowOn(IO)

 }