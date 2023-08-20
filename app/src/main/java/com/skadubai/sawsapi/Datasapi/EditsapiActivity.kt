package com.skadubai.sawsapi.Datasapi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.skadubai.sawsapi.API.RetrofitClient
import com.skadubai.sawsapi.Model.ModelRespon
import com.skadubai.sawsapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditsapiActivity : AppCompatActivity() {
    private val Alternatif by lazy { intent.getStringExtra("Alternatif") }
    private val Berat by lazy { intent.getStringExtra("Berat") }
    private val Kecacatan by lazy { intent.getStringExtra("Kecacatan") }
    private val Perilaku by lazy { intent.getStringExtra("Perilaku") }
    private val Umur by lazy { intent.getStringExtra("Umur") }
    private val Jk by lazy { intent.getStringExtra("Jk") }
    private val IdSapi by lazy { intent.getStringExtra("IdSapi") }

    lateinit var TxtAlt : EditText
    lateinit var TxtBerat : EditText
    lateinit var TxtKecacatan : EditText
    lateinit var TxtPerilaku : EditText
    lateinit var TxtUmur : EditText
    lateinit var TxtJk : EditText
    lateinit var BtnUbah : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editsapi)

        TxtAlt = findViewById(R.id.txtAlt)
        TxtBerat = findViewById(R.id.txtBerat)
        TxtKecacatan = findViewById(R.id.txtKecacatan)
        TxtPerilaku = findViewById(R.id.txtPerilaku)
        TxtUmur = findViewById(R.id.txtUmur)
        TxtJk = findViewById(R.id.txtJk)
        BtnUbah = findViewById(R.id.btnUbah)



        TxtAlt.setText(Alternatif)
        TxtBerat.setText(Berat)
        TxtKecacatan.setText(Kecacatan)
        TxtPerilaku.setText(Perilaku)
        TxtUmur.setText(Umur)
        TxtJk.setText(Jk)


        BtnUbah.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
                .setTitle("Apakah Anda Yakin Ingin Mengubah Data Sapi?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    UbahSapi()

                })
                .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                .show()
        }

    }
    private fun UbahSapi(){
        RetrofitClient.instance.UpdateSapi(
            IdSapi.toString(),
            TxtAlt.text.toString(),
            TxtBerat.text.toString(),
            TxtKecacatan.text.toString(),
            TxtPerilaku.text.toString(),
            TxtUmur.text.toString(),
            TxtJk.text.toString(),
        ).enqueue(object : Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                Toast.makeText(this@EditsapiActivity, "Edit Data Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@EditsapiActivity, DatasapiActivity::class.java))
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@EditsapiActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Hitung", t.toString())
            }

        })
    }
}
