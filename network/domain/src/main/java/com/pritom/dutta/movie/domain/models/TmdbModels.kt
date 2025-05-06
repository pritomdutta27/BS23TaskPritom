package com.pritom.dutta.movie.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Pritom Dutta on 10/5/24.
 */
interface TmdbItem : Parcelable {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterUrl: String?
    val backdropUrl: String?
    val title: String
    val voteAverage: Double
}

@Parcelize
data class Movie(
    override val id: Int,
    override val overview: String,
    @SerializedName("release_date")
    override val releaseDate: String?,
    @SerializedName("poster_path")
    override val posterUrl: String?,
    @SerializedName("backdrop_path")
    override val backdropUrl: String?,
    override val title: String,
    @SerializedName("vote_average")
    override val voteAverage: Double
) : TmdbItem


@Parcelize
data class TVShow(
    override val id: Int,
    override val overview: String,
    @SerializedName("release_date")
    override val releaseDate: String?,
    @SerializedName("poster_path")
    override val posterUrl: String?,
    @SerializedName("backdrop_path")
    override val backdropUrl: String?,
    @SerializedName("name")
    override val title: String,
    @SerializedName("vote_average")
    override val voteAverage: Double
) : TmdbItem