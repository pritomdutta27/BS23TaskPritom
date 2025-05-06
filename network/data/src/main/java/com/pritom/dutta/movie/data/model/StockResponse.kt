package com.pritom.dutta.movie.data.model

data class StockResponse(

    val products: List<Product>,
    val limit: Int,
    val skip: Int,
    val total: Int
)