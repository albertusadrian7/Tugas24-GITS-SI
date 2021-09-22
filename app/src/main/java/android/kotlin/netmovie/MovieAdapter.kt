package android.kotlin.netmovie

import android.kotlin.netmovie.model.ResultsItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        holder.judulFilm.text = data.title
        holder.tanggalRilisFilm.text = tglIndonesia(data.releaseDate.toString())
        holder.popularitas.text = data.popularity.toString()
        Glide.with(holder.itemView).load(IMAGE_BASE + data.posterPath).into(holder.gambarFilm)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var gambarFilm: ImageView = itemView.findViewById(R.id.gambarFilm)
        var judulFilm: TextView = itemView.findViewById(R.id.judulFilm)
        var tanggalRilisFilm: TextView = itemView.findViewById(R.id.tglRilis)
        var popularitas: TextView = itemView.findViewById(R.id.popularitas)
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