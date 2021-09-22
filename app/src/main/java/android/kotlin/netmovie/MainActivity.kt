package android.kotlin.netmovie

import android.kotlin.netmovie.model.MovieResponse
import android.kotlin.netmovie.model.ResultsItem
import android.kotlin.netmovie.services.RetrofitClient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val movieAdapter = MovieAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = movieAdapter
        getMovieData()
    }

    private fun getMovieData(){
        RetrofitClient.instance.getMovieList().enqueue(object: Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                if (response!!.isSuccessful){
                    response.body()?.let { tampilMovie(it) }
                    val hasil = response.body()?.results
                    for (item in hasil!!){
                        detailMovie(item!!.id)
                    }
                    val toast = Toast.makeText(this@MainActivity, "Daftar Film", Toast.LENGTH_LONG)
                    toast.show()
                } else {
                    val toast = Toast.makeText(this@MainActivity, "Gagal memberikan response", Toast.LENGTH_LONG)
                    toast.show()
                }
            }
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                val toast = Toast.makeText(this@MainActivity, "Tidak ada respon $t", Toast.LENGTH_LONG)
                toast.show()
            }
        })
    }

    private fun tampilMovie(data : MovieResponse){
        val result = data.results
        movieAdapter.setData(result as List<ResultsItem>)
    }

    private fun detailMovie(id: Int?){
        RetrofitClient
    }
}