package com.pritomdutta.bs23taskpritom.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.pritom.dutta.movie.domain.utils.NetworkResult
import com.pritomdutta.bs23taskpritom.R
import com.pritomdutta.bs23taskpritom.databinding.FragmentHomeBinding
import com.pritomdutta.bs23taskpritom.ui.adapter.StockAdapter
import com.pritomdutta.bs23taskpritom.utils.EqualSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    private val stockAdapter: StockAdapter by lazy { StockAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel.fetchStockData()
        initUi()
        apiResponse()
        return binding?.root
    }

    private fun initUi() {
        binding?.rvStock?.apply {
            addItemDecoration(EqualSpacingItemDecoration(20))
            adapter = stockAdapter
            setHasFixedSize(true)
        }
    }

    private fun apiResponse() {
        viewModel._pagingDataFlow?.observe(viewLifecycleOwner) { chatList ->
            lifecycleScope.launch { stockAdapter.submitData(chatList) }
        }
        viewModel.stockData.observe(viewLifecycleOwner) { data ->
            when (data) {
                is NetworkResult.Error -> {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
                        binding?.progressCircular?.isVisible = false
                    }
                }
                is NetworkResult.Loading-> {
                    lifecycleScope.launch(Dispatchers.Main) {
                        binding?.progressCircular?.isVisible = true
                    }
                }
                is NetworkResult.Success->{
                    lifecycleScope.launch(Dispatchers.Main) {
                        binding?.progressCircular?.isVisible = false
                    }
                }
            }
        }
    }

}