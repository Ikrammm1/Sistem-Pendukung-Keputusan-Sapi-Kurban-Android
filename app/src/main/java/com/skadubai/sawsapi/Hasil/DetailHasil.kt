package com.skadubai.sawsapi.Hasil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.skadubai.sawsapi.R

class DetailHasil : AppCompatActivity() {
    private val Alternatif by lazy { intent.getStringExtra("Alternatif") }
    private val Berat by lazy { intent.getStringExtra("Berat") }
    private val Kecacatan by lazy { intent.getStringExtra("Kecacatan") }
    private val Perilaku by lazy { intent.getStringExtra("Perilaku") }
    private val Umur by lazy { intent.getStringExtra("Umur") }
    private val Jk by lazy { intent.getStringExtra("Jk") }
    private val Nilai by lazy { intent.getStringExtra("Nilai") }
    private val Ranking by lazy { intent.getStringExtra("Ranking") }
    private val Kesimpulan by lazy { intent.getStringExtra("Kesimpulan") }

    lateinit var TxtAlt : TextView
    lateinit var TxtBerat : TextView
    lateinit var TxtKecacatan : TextView
    lateinit var TxtPerilaku : TextView
    lateinit var TxtUmur : TextView
    lateinit var TxtJk : TextView
    lateinit var TxtNilai : TextView
    lateinit var TxtRanking : TextView
    lateinit var TxtKesimpulan : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hasil)

        TxtAlt = findViewById(R.id.txtAlt)
        TxtBerat = findViewById(R.id.txtBerat)
        TxtKecacatan = findViewById(R.id.txtKecacatan)
        TxtPerilaku = findViewById(R.id.txtPerilaku)
        TxtUmur = findViewById(R.id.txtUmur)
        TxtJk = findViewById(R.id.txtJk)
        TxtNilai = findViewById(R.id.txtNilai)
        TxtRanking = findViewById(R.id.txtRanking)
        TxtKesimpulan = findViewById(R.id.txtKesimpulan)

        TxtAlt.text = Alternatif.toString()
        TxtBerat.text = Berat.toString()
        TxtKecacatan.text = Kecacatan.toString()
        TxtPerilaku.text = Perilaku.toString()
        TxtUmur.text = Umur.toString()
        TxtJk.text = Jk.toString()
        TxtNilai.text = Nilai.toString()
        TxtRanking.text = Ranking.toString()
        TxtKesimpulan.text = Kesimpulan.toString()

    }
}
