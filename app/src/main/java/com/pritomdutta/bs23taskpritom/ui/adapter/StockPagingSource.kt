package com.pritomdutta.bs23taskpritom.ui.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritom.dutta.movie.domain.models.ShowDisplayStockResponse
import com.pritom.dutta.movie.domain.repository.StockRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart

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
        val page = params.key ?: 0

        return try {
            val result = fetchStockDataOnce(page)

            when (result) {
                is NetworkResult.Success -> {
                    val data = result.data
                    onErrorAndLoad("", false)
                    LoadResult.Page(
                        data = data?.listData ?: emptyList(),
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (data?.listData.isNullOrEmpty()) null else page + 1
                    )
                }
                is NetworkResult.Error -> {
                    onErrorAndLoad(result.message ?: "Unknown error", false)
                    LoadResult.Error(Exception(result.message))
                }
                else -> {
                    // Shouldn't happen because we filtered Loading, but just in case
                    LoadResult.Error(Exception("Unexpected result"))
                }
            }

        } catch (e: Exception) {
//            onErrorAndLoad(e.message ?: "Unknown error", false)
            LoadResult.Error(e)
        }
    }

    suspend fun fetchStockDataOnce(page: Int): NetworkResult<ShowDisplayStockResponse> {
        return repository.fetchStockData(page)
            .onStart { delay(200) }
            .filter { it !is NetworkResult.Loading } // Skip loading
            .first() // Take first Success or Error
    }
}