package com.pritomdutta.bs23taskpritom.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritomdutta.bs23taskpritom.base_adapter.BasePageAdapter

class StockAdapter() :
    BasePageAdapter<ShowDisplayStockData>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = StockViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is StockViewHolder->{
                getItem(position)?.let {
                    holder.bind(it)
                }
            }
        }
    }
}