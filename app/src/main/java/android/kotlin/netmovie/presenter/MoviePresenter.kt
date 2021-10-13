package android.kotlin.netmovie.presenter

import android.content.Context
import android.kotlin.netmovie.model.MovieResponse
import android.kotlin.netmovie.model.ResultsItem
import android.kotlin.netmovie.services.RetrofitClient
import android.kotlin.netmovie.view.MovieView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(context: Context) {
    private var movieView = context as MovieView
    fun getDataMovie() {
        RetrofitClient.instance.getMovieList().enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                if (response!!.isSuccessful){
                    movieView.onDataCompleteFromApi(response.body()?.results as List<ResultsItem>)
                }
            }
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                movieView.onDataErrorFromApi(t)
            }
        })
    }
}