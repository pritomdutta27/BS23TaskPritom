package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.data.model.StockResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pritom Dutta on 06/05/25.
 */
interface ApiService {

    @GET("/products")
    suspend fun fetchStockData(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
        @Query("sortBy") sortBy: String,
        @Query("order") order: String,
    ): Response<StockResponse>

}