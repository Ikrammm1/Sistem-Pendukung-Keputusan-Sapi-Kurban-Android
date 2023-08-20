package com.skadubai.sawsapi.Model

class ModelSapi (
    val Datasapi : List<dataSapi>
){
    data class dataSapi(
        val  id_datasapi : String,
        val  alternatif : String,
        val  berat : String,
        val  kecacatan : String,
        val  perilaku : String,
        val  umur : String,
        val  jenis_kelamin : String,
        val  C1 : String,
        val  C2 : String,
        val  C3 : String,
        val  C4 : String,
        val  C5 : String
    )
}