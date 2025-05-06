package com.pritom.dutta.movie.domain.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Pritom Dutta on 10/5/24.
 */
data class TmdbWrapperModel<T>(
    val page: Int = 0,
    @SerializedName("results")
    val items: List<T> = emptyList(),
    val total_results: Int = 0,
    val total_pages: Int = 0,
)