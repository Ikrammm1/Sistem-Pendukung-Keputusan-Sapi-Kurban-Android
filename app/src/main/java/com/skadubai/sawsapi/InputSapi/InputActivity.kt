package com.skadubai.sawsapi.InputSapi

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.skadubai.sawsapi.API.RetrofitClient
import com.skadubai.sawsapi.Beranda.BerandaActivity
import com.skadubai.sawsapi.Datasapi.DatasapiActivity
import com.skadubai.sawsapi.Login.LoginActivity
import com.skadubai.sawsapi.Model.ModelRespon
import com.skadubai.sawsapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    lateinit var Alt : EditText
    lateinit var Berat : EditText
    lateinit var Kecacatan : Spinner
    lateinit var Perilaku : Spinner
    lateinit var Umur : EditText
    lateinit var Jk : Spinner
    lateinit var txtKecacatan : String
    lateinit var txtPerilaku : String
    lateinit var txtJk : String
    lateinit var BtnTambah : Button
    private lateinit var profil : SharedPreferences
    lateinit var  Id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        Id = profil.getString("id_user", null)!!

        Alt = findViewById(R.id.et_alt)
        Berat = findViewById(R.id.et_berat)
        Kecacatan = findViewById(R.id.et_kecacatan)
        Perilaku = findViewById(R.id.et_perilaku)
        Umur = findViewById(R.id.et_umur)
        Jk = findViewById(R.id.et_jk)
        BtnTambah = findViewById(R.id.btn_tambah)

        Kecacatan.onItemSelectedListener  = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                txtKecacatan = parent?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        Perilaku.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                txtPerilaku = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        Jk.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                txtJk = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        BtnTambah.setOnClickListener {
            when {
                Alt.text.toString() == "" -> {
                    Alt.error = "Nama / Lebel Tidak Boleh Kosong!"
                }
                Berat.text.toString() == "" -> {
                    Berat.error = "Berat Tidak Boleh Kosong!"
                }
                Berat.text.toString() == "0" -> {
                    Berat.error = "Berat Tidak Boleh 0!"
                }
                Umur.text.toString() == "" -> {
                    Umur.error = "Umur Tidak Boleh Kosong!"
                }
                Umur.text.toString() == "0" -> {
                    Umur.error = "Umur Tidak Boleh 0!"
                }
                else ->{
                    var alertDialog = AlertDialog.Builder(this)
                        .setTitle("Apakah Anda ingin Menambahkan Data Sapi?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                            Input()

                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                        .show()

                }

            }
        }
    }
    private fun Input(){
        RetrofitClient.instance.InputSapi(
            Id,
            Alt.text.toString(),
            Berat.text.toString(),
            txtKecacatan,
            txtPerilaku,
            Umur.text.toString(),
            txtJk
        ).enqueue(object :Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if (response.isSuccessful){
                    Toast.makeText(this@InputActivity, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@InputActivity, DatasapiActivity::class.java))
                }else{
                    Toast.makeText(this@InputActivity, "Terjadi Kesalahan. Perhatikan Penulisan Berat dan Umur", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@InputActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Register", t.toString())
            }

        })
    }
}
