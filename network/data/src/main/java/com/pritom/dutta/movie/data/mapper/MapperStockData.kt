package com.pritom.dutta.movie.data.mapper

import com.pritom.dutta.movie.data.model.StockResponse
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritom.dutta.movie.domain.models.ShowDisplayStockResponse

fun StockResponse.toShowDisplayStockResponse(): ShowDisplayStockResponse {
    return ShowDisplayStockResponse(
        listData = this.products.map {
            ShowDisplayStockData(
                imageUrl = it.thumbnail,
                title = it.title,
                price = it.price.toString(),
                discountPrice = "%.2f".format(it.discountPercentage.discountCalculate(it.price)),
                rating = it.rating.toString(),
                category = it.tags,
                stockStatus = if (it.stock <= 5 && it.stock != 0) "Only ${it.stock} left!" else "",
                isDiscountAvailable = it.discountPercentage > 0f
            )
        },
        limit = this.limit,
        skip = this.skip,
        total = this.total
    )
}

fun Double.discountCalculate(price: Double): Double {
    return price - (price * this / 100)
}