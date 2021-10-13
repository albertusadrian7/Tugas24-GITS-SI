package android.kotlin.netmovie.presenter

import android.content.Context
import android.kotlin.netmovie.model.DetailMovieResponse
import android.kotlin.netmovie.services.RetrofitClient
import android.kotlin.netmovie.view.DetailMovieView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMoviePresenter(context: Context) {
    private var detailMovieView = context as DetailMovieView
    fun getDetailMovie(idMovie: String?) {
        RetrofitClient.instance.getMovieDetail(idMovie!!.toInt())
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>?,
                    response: Response<DetailMovieResponse>?
                ) {
                    if (response!!.isSuccessful){
                        detailMovieView.onDetailCompleteFromApi(response?.body()!!)
                    }
                }
                override fun onFailure(call: Call<DetailMovieResponse>?, t: Throwable?) {
                    detailMovieView.onDetailErrorFromApi(t)
                }
            })
    }
}