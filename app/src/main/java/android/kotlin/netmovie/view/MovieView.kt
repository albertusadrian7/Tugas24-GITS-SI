package android.kotlin.netmovie.view

import android.kotlin.netmovie.model.ResultsItem

interface MovieView {
    fun onDataCompleteFromApi(movie: List<ResultsItem>)
    fun onDataErrorFromApi(throwable: Throwable?)
}