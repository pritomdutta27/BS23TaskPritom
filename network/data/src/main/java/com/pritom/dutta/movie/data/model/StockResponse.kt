package com.pritom.dutta.movie.data.model

data class StockResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)