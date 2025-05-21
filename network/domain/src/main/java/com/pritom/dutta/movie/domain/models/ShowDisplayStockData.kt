package com.pritom.dutta.movie.domain.models

data class ShowDisplayStockData(
    val imageUrl: String,
    val title: String,
    val price: String,
    val discountPrice: String,
    val rating: String,
    val category: List<String>,
    val stockStatus: String,
    val isDiscountAvailable: Boolean,
)

data class ShowDisplayStockResponse(
    val listData: List<ShowDisplayStockData>,
    val limit: Int,
    val skip: Int,
    val total: Int
)
