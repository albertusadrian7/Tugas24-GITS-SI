package android.kotlin.netmovie.adapter

import android.content.Intent
import android.kotlin.netmovie.Constants
import android.kotlin.netmovie.Constants.Companion.IMAGE_BASE
import android.kotlin.netmovie.DetailMovieActivity
import android.kotlin.netmovie.R
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listMovie[position]
        holder.judulFilm.text = data.title
        holder.tanggalRilisFilm.text = Constants.tglIndonesia(data.releaseDate.toString())
        holder.ratingBar.rating = ((data.voteAverage)!! /2.toFloat()).toFloat()
        Glide.with(holder.itemView).load(IMAGE_BASE + data.posterPath).into(holder.gambarFilm)
        holder.pilihFilm.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailMovieActivity::class.java)
            intent.putExtra("id_movie", data.id.toString())
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

}