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
import androidx.paging.LoadState
import com.pritom.dutta.movie.domain.utils.NetworkResult
import com.pritom.dutta.movie.domain.utils.NetworkUtils
import com.pritomdutta.bs23taskpritom.R
import com.pritomdutta.bs23taskpritom.databinding.FragmentHomeBinding
import com.pritomdutta.bs23taskpritom.ui.adapter.PhotoLoadStateAdapter
import com.pritomdutta.bs23taskpritom.ui.adapter.StockAdapter
import com.pritomdutta.bs23taskpritom.utils.EqualSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val stockAdapter: StockAdapter by lazy { StockAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        if (NetworkUtils.isConnected(requireContext())) {
            fetchStockData()
            observeViewModel()
        } else {
            showRetryState()
        }
    }

    private fun initUi() = binding.run {
        rvStock.apply {
            setHasFixedSize(true)
            addItemDecoration(EqualSpacingItemDecoration(20))
            adapter = stockAdapter.withLoadStateFooter(
                footer = PhotoLoadStateAdapter { stockAdapter.retry() }
            )
        }

        swipeRefresh.setOnRefreshListener {
            showLoadingState()
            stockAdapter.refresh()
        }

        buttonRetry.setOnClickListener {
            fetchStockData()
            observeViewModel()
        }
    }

    private fun fetchStockData() {
        viewModel.fetchStockData()
        showLoadingState()
    }

    private fun observeViewModel() {
        viewModel.stockData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> showLoadingState()
                is NetworkResult.Success -> hideLoadingState()
                is NetworkResult.Error -> showRetryState()
            }
        }

        viewModel.pagingDataFlow?.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                stockAdapter.submitData(pagingData)
                hideLoadingState()
            }
        }
    }

    private fun showLoadingState() = binding.run {
        progressCircular.isVisible = true
        buttonRetry.isVisible = false
    }

    private fun hideLoadingState() = binding.run {
        progressCircular.isVisible = false
        swipeRefresh.isRefreshing = false
        buttonRetry.isVisible = false
    }

    private fun showRetryState() = binding.run {
        progressCircular.isVisible = false
        swipeRefresh.isRefreshing = false
        buttonRetry.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
