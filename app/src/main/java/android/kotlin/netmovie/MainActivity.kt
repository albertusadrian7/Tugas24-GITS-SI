package android.kotlin.netmovie

import android.kotlin.netmovie.adapter.MovieAdapter
import android.kotlin.netmovie.model.ResultsItem
import android.kotlin.netmovie.presenter.MoviePresenter
import android.kotlin.netmovie.view.MovieView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MoviePresenter(this).getDataMovie()
    }

    override fun onDataCompleteFromApi(movieData: List<ResultsItem>) {
        val movieAdapter = MovieAdapter(movieData as ArrayList<ResultsItem>)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = movieAdapter
        Toast.makeText(this, "Daftar Movie", Toast.LENGTH_SHORT).show()
    }

    override fun onDataErrorFromApi(throwable: Throwable?) {
        Toast.makeText(this, "Tidak ada respon $throwable", Toast.LENGTH_LONG).show()
        error("Tidak ada respon: ${throwable?.localizedMessage}")
    }

}