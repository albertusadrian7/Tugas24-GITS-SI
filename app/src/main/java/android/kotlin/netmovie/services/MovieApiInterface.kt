package android.kotlin.netmovie.services

import android.kotlin.netmovie.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface{
    @GET("https://api.themoviedb.org/3/movie/popular?api_key=2404aa109b85ad4c32e064055f004e40")
    fun getMovieList(): Call<MovieResponse>
}