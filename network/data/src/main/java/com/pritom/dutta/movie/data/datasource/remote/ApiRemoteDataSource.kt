package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.data.utils.onResponse
import pritom.dutta.test.OpenForTesting
import retrofit2.http.Query
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 06/05/25.
 */

@OpenForTesting
class ApiRemoteDataSource @Inject constructor(private val api: ApiService) {
    suspend operator fun invoke(
        page: Int = 1,
        limit: Int = 10,
        sortBy: String = "price%2Ctitle",
        order: String = "asc"
    ) = api.fetchStockData(limit = limit, skip = page, sortBy = sortBy, order = order).onResponse()
}