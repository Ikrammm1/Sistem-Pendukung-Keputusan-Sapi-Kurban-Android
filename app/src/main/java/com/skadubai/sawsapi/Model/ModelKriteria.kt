package com.skadubai.sawsapi.Model

class ModelKriteria (
    val Kriteria : List<dataKriteria>
) {
    data class dataKriteria(
        val id_kriteria: String?,
        val kriteria: String?,
        val sifat: String?,
        val bobot: String?

    )
}