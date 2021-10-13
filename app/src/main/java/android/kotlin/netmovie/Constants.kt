package android.kotlin.netmovie

class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
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
}