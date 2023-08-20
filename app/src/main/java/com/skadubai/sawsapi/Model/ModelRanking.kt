package com.skadubai.sawsapi.Model

class ModelRanking (
    val Ranking : List<dataRanking>
){
    data class dataRanking(
        val  id_datasapi : String,
        val  alternatif : String,
        val  berat : String,
        val  kecacatan : String,
        val  perilaku : String,
        val  umur : String,
        val  jenis_kelamin : String,
        val  nilai : String,
        val  ranking : String,
        val  kesimpulan : String,
    )
}
