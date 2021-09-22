package android.kotlin.netmovie

import android.kotlin.netmovie.R.layout.activity_detail_movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*


class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_movie)
        val intent = intent
        val judulFilm = intent.getStringExtra("judulFilm")
        val tglRilisFilm = intent.getStringExtra("tanggalRilisFilm")
        val overviewFilm = intent.getStringExtra("overviewFilm")
        val rateFilm = intent.getFloatExtra("rateFilm",0.0F)
        val ratingFilm = intent.getStringExtra("ratingFilm")
        val popularitasFilm = intent.getStringExtra("popularitasFilm")
        val gambarFilm = intent.getStringExtra("gambarFilm")
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        judulDetailFilm.setText(judulFilm)
        tglRilisDetailFilm.setText(tglRilisFilm)
        rateBar.setRating(rateFilm)
        rating.setText(ratingFilm)
        popularitas.setText(popularitasFilm)
        detailOverview.setText(overviewFilm)
        Glide.with(this).load(IMAGE_BASE + gambarFilm).into(gambarDetailFilm)
    }

}