package com.pritomdutta.bs23taskpritom.ui.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritom.dutta.movie.domain.models.ShowDisplayStockResponse
import com.pritom.dutta.movie.domain.repository.StockRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult

class StockPagingSource(
    private val repository: StockRepository,
    private val onErrorAndLoad:(message: String, isLoad: Boolean)->Unit
): PagingSource<Int, ShowDisplayStockData>() {

    override fun getRefreshKey(state: PagingState<Int, ShowDisplayStockData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowDisplayStockData> {
       return try {
           val position = params.key ?: 0
           var dataRes:ShowDisplayStockResponse? = null

           val remoteResponse = repository.fetchStockData(position)
           remoteResponse.collect {  data->
               when(data){
                   is NetworkResult.Error-> {
                       onErrorAndLoad(data.message ?: "", false)
                   }
                   is NetworkResult.Loading -> {
                       onErrorAndLoad("", true)
                   }
                   is NetworkResult.Success -> {
                       onErrorAndLoad("", false)
                       dataRes = data.data
                   }
               }

           }
           LoadResult.Page(
               data = dataRes?.listData ?: emptyList(),
               prevKey = if (position == 1 || position == 0) null else position - 1,
               nextKey = if (dataRes?.total == position || dataRes?.total == 0) null else position + 1
           )

       }catch (e: Exception) {
           LoadResult.Error(e)
       }

    }
}