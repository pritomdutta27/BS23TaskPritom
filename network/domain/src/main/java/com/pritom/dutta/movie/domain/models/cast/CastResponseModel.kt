package com.pritom.dutta.movie.domain.models.cast

data class CastResponseModel(
    val cast: List<Cast>,
    val crew: List<Cast>,
    val id: Int
)