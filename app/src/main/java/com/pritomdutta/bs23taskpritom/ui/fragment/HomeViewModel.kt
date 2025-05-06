package com.pritomdutta.bs23taskpritom.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritom.dutta.movie.domain.repository.StockRepository
import com.pritomdutta.bs23taskpritom.ui.adapter.StockPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: StockRepository) :
    ViewModel() {

//    private val _stockData = MutableLiveData<NetworkResult<ShowDisplayStockResponse>>()
//    val stockData: LiveData<NetworkResult<ShowDisplayStockResponse>>
//        get() = _stockData

    var _pagingDataFlow: LiveData<PagingData<ShowDisplayStockData>>? = null

//    val pagingDataFlow = Pager(
//        config = PagingConfig(pageSize = 10, maxSize = 200),
//        pagingSourceFactory = {
//            StockPagingSource(onRes = { page ->
//                data(page) // Now it returns ShowDisplayStockResponse
//            })
//        }
//    ).flow.cachedIn(viewModelScope)

    fun fetchStockData() {
        _pagingDataFlow = Pager(
            config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = {
                StockPagingSource(repository)
            }
        ).liveData
    }


//    suspend fun data(page: Int){
//        repository.fetchStockData(page)
//    }
}