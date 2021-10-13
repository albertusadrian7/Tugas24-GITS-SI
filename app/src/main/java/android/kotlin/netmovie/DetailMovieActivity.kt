package android.kotlin.netmovie

import android.kotlin.netmovie.Constants.Companion.IMAGE_BASE
import android.kotlin.netmovie.R.layout.activity_detail_movie
import android.kotlin.netmovie.model.DetailMovieResponse
import android.kotlin.netmovie.presenter.DetailMoviePresenter
import android.kotlin.netmovie.view.DetailMovieView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*


class DetailMovieActivity : AppCompatActivity(), DetailMovieView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_movie)
        var idMovie = intent.getStringExtra("id_movie")
        DetailMoviePresenter(this).getDetailMovie(idMovie)
    }
    override fun onDetailCompleteFromApi(detailMovie: DetailMovieResponse) {
        showDetailMovie(detailMovie)
    }

    override fun onDetailErrorFromApi(throwable: Throwable?) {
        Toast.makeText(this, "Tidak ada respon $throwable", Toast.LENGTH_LONG).show()
        error("Tidak ada respon: ${throwable?.localizedMessage}")
    }

    private fun showDetailMovie(body: DetailMovieResponse) {
        val ratingAge = body.adult
        var ketRating = if (ratingAge == true) {
            "Dewasa"
        } else {
            "Remaja"
        }
        judulDetailFilm.text = body.title.toString()
        tglRilisDetailFilm.text = Constants.tglIndonesia(body.releaseDate.toString())
        detailOverview.text = body.overview.toString()
        rateBar.rating = ((body.voteAverage)!! /2.toFloat()).toFloat()
        rating.text = ketRating
        popularitas.text = body.popularity.toString()
        Glide.with(this).load(IMAGE_BASE + body.posterPath).into(gambarDetailFilm)
    }

}