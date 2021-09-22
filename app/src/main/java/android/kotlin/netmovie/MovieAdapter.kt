package android.kotlin.netmovie

import android.content.Intent
import android.kotlin.netmovie.model.ResultsItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val listMovie: ArrayList<ResultsItem>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    fun setData(data : List<ResultsItem>){
        listMovie.clear()
        listMovie.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val data = listMovie[position]
        val rating = data.adult
        var ketRating = ""
        if (rating == true) {
            ketRating = "Dewasa"
        } else {
            ketRating = "Remaja"
        }
        holder.judulFilm.text = data.title
        holder.tanggalRilisFilm.text = tglIndonesia(data.releaseDate.toString())
        holder.ratingBar.rating = ((data.voteAverage)!! /2.toFloat()).toFloat()
        Glide.with(holder.itemView).load(IMAGE_BASE + data.posterPath).into(holder.gambarFilm)
        holder.pilihFilm.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailMovieActivity::class.java)
            intent.putExtra("judulFilm", data.title.toString())
            intent.putExtra("tanggalRilisFilm", tglIndonesia(data.releaseDate.toString()))
            intent.putExtra("rateFilm", ((data.voteAverage)!! /2.toFloat()).toFloat())
            intent.putExtra("ratingFilm", ketRating)
            intent.putExtra("gambarFilm", data.posterPath.toString())
            intent.putExtra("popularitasFilm", data.popularity.toString())
            intent.putExtra("overviewFilm", data.overview)
            print("Poster path: ${data.posterPath}")
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listMovie.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var gambarFilm: ImageView = itemView.findViewById(R.id.gambarFilm)
        var judulFilm: TextView = itemView.findViewById(R.id.judulFilm)
        var tanggalRilisFilm: TextView = itemView.findViewById(R.id.tglRilis)
        var ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        var pilihFilm: CardView = itemView.findViewById(R.id.card_layout)
    }

    fun tglIndonesia (tgl: String): String{
        val tgl = tgl.split("-")
        val bulan = when(tgl[1]){
            "01" -> "Januari"
            "02" -> "Februari"
            "03" -> "Maret"
            "04" -> "April"
            "05" -> "Mei"
            "06" -> "Juni"
            "07" -> "Juli"
            "08" -> "Agustus"
            "09" -> "September"
            "10" -> "Oktober"
            "11" -> "November"
            "12" -> "Desember"
            else -> "Terjadi kesalahan"
        }
        var tglIndonesia = "${tgl[2]} $bulan ${tgl[0]}"
        return tglIndonesia
    }

}