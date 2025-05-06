package com.pritomdutta.bs23taskpritom.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritomdutta.bs23taskpritom.base_adapter.BaseViewHolder
import com.pritomdutta.bs23taskpritom.databinding.ItemStockBinding

class StockViewHolder (
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<ItemStockBinding>(
    binding = ItemStockBinding.inflate(inflater, parent, false)
) {

    fun bind(data: ShowDisplayStockData){

    }
}