package com.pritomdutta.bs23taskpritom.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritom.dutta.movie.domain.repository.StockRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: StockRepository) :
    ViewModel() {

    private val _stockData = MutableLiveData<NetworkResult<List<ShowDisplayStockData>>>()
    val stockData: LiveData<NetworkResult<List<ShowDisplayStockData>>>
        get() = _stockData

    fun fetchStockData() {
        viewModelScope.launch {
            repository.fetchStockData(0).collect { data ->
                _stockData.postValue(data)
            }
        }
    }

}