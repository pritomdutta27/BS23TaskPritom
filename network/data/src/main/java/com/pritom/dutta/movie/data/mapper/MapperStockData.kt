package com.pritom.dutta.movie.data.mapper

import com.pritom.dutta.movie.data.model.StockResponse
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData

fun StockResponse.toStockDisplayData(): List<ShowDisplayStockData> {
    return this.products.map {
        ShowDisplayStockData(
            imageUrl = it.thumbnail,
            title = it.title,
            price = it.price.toString(),
            rating = it.rating.toString(),
            category = it.tags,
            stockStatus = if (it.stock <= 5) "Only ${it.stock} left!" else ""
        )
    }
}