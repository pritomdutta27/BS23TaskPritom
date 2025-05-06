package com.pritom.dutta.movie.domain.models

data class ShowDisplayStockData(
    val imageUrl: String,
    val title: String,
    val price: String,
    val rating: String,
    val category: List<String>,
    val stockStatus: String,
)
