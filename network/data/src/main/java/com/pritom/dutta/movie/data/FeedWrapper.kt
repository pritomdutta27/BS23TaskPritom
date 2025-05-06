package com.pritom.dutta.movie.data

import android.os.Parcelable
import androidx.annotation.StringRes
import com.pritom.dutta.movie.domain.models.MovieDetailsModel
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.models.cast.CastResponseModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.Flow


@Parcelize
enum class SortType : Parcelable {
    TRENDING,
    MOST_POPULAR,
    HIGHEST_RATED,
    UPCOMING,
    NOW_PLAYING,
    DISCOVER,
}

class FeedWrapper<T>(
    val feeds: Flow<NetworkResult<TmdbWrapperModel<T>>>,
    @StringRes val sortTypeResourceId: Int,
    val sortType: SortType
)

class DetailWrapper(
//    val videos: List<MediaStore.Video>,
    val cast: Flow<NetworkResult<CastResponseModel>>,
    val details: Flow<NetworkResult<MovieDetailsModel>>
)