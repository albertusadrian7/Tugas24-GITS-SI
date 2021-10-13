package android.kotlin.netmovie.services

import android.kotlin.netmovie.model.DetailMovieResponse
import android.kotlin.netmovie.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface{
    @GET("movie/popular?api_key=2404aa109b85ad4c32e064055f004e40&language=en-US&page=1")
    fun getMovieList(): Call<MovieResponse>

    @GET("movie/{movie_id}?api_key=2404aa109b85ad4c32e064055f004e40&language=en&page=1")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int
    ): Call<DetailMovieResponse>
}

