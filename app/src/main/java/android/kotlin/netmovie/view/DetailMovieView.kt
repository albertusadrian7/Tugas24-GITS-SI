package android.kotlin.netmovie.view

import android.kotlin.netmovie.model.DetailMovieResponse

interface DetailMovieView {
    fun onDetailCompleteFromApi(detailMovie: DetailMovieResponse)
    fun onDetailErrorFromApi(throwable: Throwable?)
}