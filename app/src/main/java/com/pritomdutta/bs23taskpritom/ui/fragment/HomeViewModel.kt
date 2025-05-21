package com.pritomdutta.bs23taskpritom.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritom.dutta.movie.domain.models.ShowDisplayStockResponse
import com.pritom.dutta.movie.domain.repository.StockRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import com.pritomdutta.bs23taskpritom.ui.adapter.StockPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: StockRepository) :
    ViewModel() {

    private val _stockData = MutableLiveData<NetworkResult<Boolean>>()
    val stockData: LiveData<NetworkResult<Boolean>>
        get() = _stockData

    var pagingDataFlow: LiveData<PagingData<ShowDisplayStockData>>? = null

    fun fetchStockData() {
        pagingDataFlow = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                StockPagingSource(repository) { mez, isLoad ->
                    if (isLoad && mez.isEmpty()) {
                        _stockData.postValue(NetworkResult.Loading())
                    } else if (!mez.isEmpty()) {
                        _stockData.postValue(NetworkResult.Error(code = 404, message = mez))
                    } else {
                        _stockData.postValue(NetworkResult.Success(false))
                    }

                }
            }
        ).liveData
    }
}